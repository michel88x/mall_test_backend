package com.michel.mall_test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michel.mall_test.entity.Token;
import com.michel.mall_test.entity.User;
import com.michel.mall_test.extra.dto.authentication.*;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.enums.Gender;
import com.michel.mall_test.extra.enums.Role;
import com.michel.mall_test.extra.enums.TokenType;
import com.michel.mall_test.repository.TokenRepository;
import com.michel.mall_test.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final UserVerificationCodeService userVerificationCodeService;
    private final ReferralCodeService referralCodeService;


    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager,
                                 TokenRepository tokenRepository,
                                 UserVerificationCodeService userVerificationCodeService,
                                 ReferralCodeService referralCodeService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
        this.userVerificationCodeService=  userVerificationCodeService;
        this.referralCodeService = referralCodeService;
    }

    @Value("${application.referral.code-value}")
    private Integer referralCodeValue;


    public BaseResponse register(RegisterRequest request) {
        Optional<User> findUser = repository.findByEmail(request.getEmail());
        BaseResponse res = new BaseResponse();
        if(findUser.isPresent()){
            res.setSuccess(false);
            res.setMessage("Your email is already existed");
            return res;
        }
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmailVerified(false);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirthdate(formatter.parse(request.getBirthdate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        user.setGender("male".equals(request.getGender())? Gender.MALE : Gender.FEMALE);
        user.setPoints(0);
        user.setPass(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT);
        //Save the new user
        User savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        //Save the user tokens
        saveUserToken(savedUser, jwtToken);
        //Save the user verification code and send via email
        userVerificationCodeService.addNewVerificationCode(savedUser);
        //Save the user referral code
        referralCodeService.generateUserReferralCode(savedUser);
        //Check if Referral Code for another user used
        if(request.getReferralCode() != null && !request.getReferralCode().isEmpty()){
            Boolean useCodeResult = referralCodeService.useReferralCode(request.getReferralCode(), savedUser);
            if(useCodeResult){
                savedUser.setPoints(referralCodeValue);
            }
        }
        res.setSuccess(true);
        res.setMessage("Registered Successfully");
        res.setData(UserDto.UserDtoBuilder.anUserDto()
                .withId(savedUser.getId())
                .withName(savedUser.getName())
                        .withEmail(savedUser.getEmail())
                        .withPhoneNumber(savedUser.getPhoneNumber())
                        .withBirthDate(savedUser.getBirthdate())
                        .withGender(savedUser.getGender())
                        .withPoints(savedUser.getPoints())
                        .withEmailVerified(savedUser.getEmailVerified())
                        .withToken(TokenDto.TokenDtoBuilder.aTokenDto()
                                .withToken(jwtToken)
                                .withRefreshToken(refreshToken)
                                .build())
                .build());
        return res;
    }


    @Transactional
    public BaseResponse authenticate(AuthenticationRequest request) {
        Optional<User> findUser = repository.findByEmail(request.getEmail());
        BaseResponse res = new BaseResponse();
        if(findUser.isPresent() && passwordEncoder.matches(request.getPassword(), findUser.get().getPassword())){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            /// The code won't continue to the following code unless the email and password was correct
            User user = repository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            if(!user.getEmailVerified()) {
                userVerificationCodeService.addNewVerificationCode(user);
            }
            res.setSuccess(true);
            res.setMessage("Got successfully");
            res.setData(UserDto.UserDtoBuilder.anUserDto()
                    .withId(user.getId())
                    .withName(user.getName())
                    .withEmail(user.getEmail())
                    .withPhoneNumber(user.getPhoneNumber())
                    .withBirthDate(user.getBirthdate())
                    .withGender(user.getGender())
                    .withPoints(user.getPoints())
                    .withEmailVerified(user.getEmailVerified())
                    .withToken(TokenDto.TokenDtoBuilder.aTokenDto()
                            .withToken(jwtToken)
                            .withRefreshToken(refreshToken)
                            .build())
                    .build());
        }else{
            res.setSuccess(false);
            res.setMessage("Username or password is wrong");
        }
        return res;
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /// Extract the authorization value from the header
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        /// Check if there is no token
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        /// Extracting the JWTToken
        refreshToken = authHeader.substring(7);
        /// Extract the user Email from the token
        userEmail = jwtService.extractUsername(refreshToken );
        /// Check if the userEmail is existed and if the user is not yet authenticated
        if(userEmail != null){
            /// Get the user
            var user = this.repository.findByEmail(userEmail).orElseThrow();
            /// Check if the token is still valid
            if(jwtService.isTokenValid(refreshToken , user)){
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var tokenDto = new TokenDto();
                tokenDto.setToken(accessToken);
                tokenDto.setRefreshToken(refreshToken);
                BaseResponse res = new BaseResponse();
                res.setSuccess(true);
                res.setMessage("Refreshed successfully");
                res.setData(tokenDto);
                /// Return the response
                new ObjectMapper().writeValue(response.getOutputStream(), res);
            }
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = new Token();
        token.setUser(user);
        token.setToken(jwtToken);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

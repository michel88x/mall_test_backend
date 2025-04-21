package com.michel.mall_test.controller;

import com.michel.mall_test.entity.User;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.exceptions.UserNotFoundException;
import com.michel.mall_test.repository.UserRepository;
import com.michel.mall_test.service.JwtService;
import com.michel.mall_test.service.UserVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/code")
public class UserVerificationCodeController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserVerificationCodeService userVerificationCodeService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/resend")
    public ResponseEntity<?> resend(@RequestHeader("Authorization") String authorization){
        Optional<User> user = jwtService.getUserByToken(authorization);
        if(user.isPresent()){
            return ResponseEntity.ok(userVerificationCodeService.addNewVerificationCode(user.get()));
        }else{
            throw new UserNotFoundException("No user found");
        }
    }

    @PostMapping(value = "/verify", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> verify(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("code") String code
    ){
        Optional<User> user = jwtService.getUserByToken(authorization);
        if(user.isPresent()){
            return ResponseEntity.ok(userVerificationCodeService.verifyCode(user.get(), code));
        }else{
            throw new UserNotFoundException("No user found");
        }
    }
}

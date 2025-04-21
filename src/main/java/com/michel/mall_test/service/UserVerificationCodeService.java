package com.michel.mall_test.service;

import com.michel.mall_test.entity.User;
import com.michel.mall_test.entity.UserVerificationCode;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.repository.UserRepository;
import com.michel.mall_test.repository.UserVerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class UserVerificationCodeService {

    @Autowired
    private UserVerificationCodeRepository repository;

    @Autowired
    private GeneralService generalService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public BaseResponse addNewVerificationCode(User user){
        Optional<UserVerificationCode> code = repository.findOneByUser(user);
        if(code.isPresent()){
            repository.deleteRow(user.getId());
        }
        String newCode = generalService.generateCode();
        emailService.sendVerificationCode(newCode, user.getEmail());
        repository.save(UserVerificationCode.UserVerificationCodeBuilder.anUserVerificationCode()
                        .withCode(newCode)
                        .withUser(user)
                .build());
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Verification code sent successfully")
                .withData(null)
                .build();
    }

    @Transactional
    public BaseResponse verifyCode(User user, String code){
        Optional<UserVerificationCode> result = repository.findOneByUserAndCode(user, code);
        if(result.isPresent()){
            userRepository.updateUserEmailVerified(user.getId());
            repository.deleteRow(user.getId());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Your code is correct")
                    .withData(null)
                    .build();
        }else{
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(false)
                    .withMessage("Your code is wrong, please enter another one")
                    .withData(null)
                    .build();
        }
    }
}

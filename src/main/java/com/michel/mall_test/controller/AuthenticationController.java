package com.michel.mall_test.controller;


import com.michel.mall_test.extra.dto.authentication.AuthenticationRequest;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.dto.authentication.RegisterRequest;
import com.michel.mall_test.service.AuthenticationService;
import com.michel.mall_test.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService service;
    private final EmailService emailService;


    public AuthenticationController(AuthenticationService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }


    @Valid
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<BaseResponse> register(
            @Valid
            @ModelAttribute
            RegisterRequest request
            ){
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<BaseResponse> authenticate(
            @Valid
            @ModelAttribute
            AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}

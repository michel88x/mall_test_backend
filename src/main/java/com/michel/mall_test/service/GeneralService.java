package com.michel.mall_test.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GeneralService {
    public String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 6-digit code
        return String.valueOf(code);
    }
}

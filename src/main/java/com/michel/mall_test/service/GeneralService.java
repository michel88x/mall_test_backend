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

    public String generateRandomString(){
        String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder value = new StringBuilder();
        Random rnd = new Random();
        while (value.length() < 7) {
            int index = (int) (rnd.nextFloat() * dictionary.length());
            value.append(dictionary.charAt(index));
        }
        return value.toString();
    }
}

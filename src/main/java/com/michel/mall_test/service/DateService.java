package com.michel.mall_test.service;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class DateService {

    String getTDateTime(){
        ZonedDateTime now = ZonedDateTime.now();
        String nowString = now.toString();
        nowString = nowString.substring(0, nowString.indexOf("."));
        return nowString;
    }

    String getIsoNowDateTime(){
        String nowString = getTDateTime();
        nowString = nowString + ".000Z";
        return nowString;
    }

    String getNowDateTime(){
        String nowString = getTDateTime();
        nowString = nowString.replace("T", " ");
        return nowString;
    }
}

package com.michel.mall_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class EmailService {

    @Autowired
    private MailSender mailSender;

    @Value("${spring.mail.username}")
    private String myEmail;

    @Async
    public void sendVerificationCode(String code, String toEmail) {
        String subject = "Your Verification Code";
        String message = "Your verification code is: " + code;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toEmail);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom(myEmail);

        mailSender.send(email);
    }
}

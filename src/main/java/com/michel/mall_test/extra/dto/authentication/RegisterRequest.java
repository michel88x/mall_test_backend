package com.michel.mall_test.extra.dto.authentication;

import com.michel.mall_test.extra.enums.Gender;
import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class RegisterRequest {
    @NotEmpty(message = "You must enter your name")
    private String name;
    @NotEmpty(message = "You must enter your email")
    @Email(message = "You must enter your email the write way")
    private String email;
    @NotEmpty(message = "You must enter your phone number")
    @Size(min = 9, max = 10, message = "Your phone number must be 9 or 10 digits")
    private String phoneNumber;
    @NotEmpty(message = "You must pick your birthdate")
    @Past(message = "Your birthdate must be a date in the past")
    private String birthdate;
    @NotEmpty(message = "You must pick your gender")
    private String gender;
    @NotEmpty(message = "You must write your password")
    @Min(value = 8, message = "Your password must be 8 characters length at least")
    private String password;

    public RegisterRequest() {}

    public RegisterRequest(String name, String email, String phoneNumber, String birthdate, String gender, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static final class RegisterRequestBuilder {
        private String name;
        private String email;
        private String phoneNumber;
        private String birthdate;
        private String gender;
        private String password;

        private RegisterRequestBuilder() {
        }

        public static RegisterRequestBuilder aRegisterRequest() {
            return new RegisterRequestBuilder();
        }

        public RegisterRequestBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RegisterRequestBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public RegisterRequestBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public RegisterRequestBuilder withBirthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public RegisterRequestBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public RegisterRequestBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public RegisterRequest build() {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setName(name);
            registerRequest.setEmail(email);
            registerRequest.setPhoneNumber(phoneNumber);
            registerRequest.setBirthdate(birthdate);
            registerRequest.setGender(gender);
            registerRequest.setPassword(password);
            return registerRequest;
        }
    }
}

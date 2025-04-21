package com.michel.mall_test.extra.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {
    @NotEmpty(message = "You must enter your email")
    @Email(message = "You must enter your email the write way")
    private String email;
    @NotEmpty(message = "You must write your password")
    @Size(min = 8, message = "Your password must be 8 characters length at least")
    private String password;


    public AuthenticationRequest() {}


    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public static final class AuthenticationRequestBuilder {
        private String email;
        private String password;

        private AuthenticationRequestBuilder() {
        }

        public static AuthenticationRequestBuilder anAuthenticationRequest() {
            return new AuthenticationRequestBuilder();
        }

        public AuthenticationRequestBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public AuthenticationRequestBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationRequest build() {
            AuthenticationRequest authenticationRequest = new AuthenticationRequest();
            authenticationRequest.setEmail(email);
            authenticationRequest.setPassword(password);
            return authenticationRequest;
        }
    }
}

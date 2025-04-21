package com.michel.mall_test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity
public class UserVerificationCode extends BaseEntity{

    @Column
    private String code;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserVerificationCode() {}

    public UserVerificationCode(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public UserVerificationCode(String code, User user) {
        this.code = code;
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static final class UserVerificationCodeBuilder {
        private String code;
        private User user;

        private UserVerificationCodeBuilder() {
        }

        public static UserVerificationCodeBuilder anUserVerificationCode() {
            return new UserVerificationCodeBuilder();
        }

        public UserVerificationCodeBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public UserVerificationCodeBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public UserVerificationCode build() {
            UserVerificationCode userVerificationCode = new UserVerificationCode();
            userVerificationCode.setCode(code);
            userVerificationCode.setUser(user);
            return userVerificationCode;
        }
    }
}

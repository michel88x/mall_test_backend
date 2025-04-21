package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ReferralCode extends BaseEntity{

    @Column
    private String code;

    @Column
    private Integer value;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public ReferralCode() {

    }

    public ReferralCode(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public ReferralCode(String code, Integer value, User user) {
        this.code = code;
        this.value = value;
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static final class ReferralCodeBuilder {
        private String code;
        private Integer value;
        private User user;

        private ReferralCodeBuilder() {
        }

        public static ReferralCodeBuilder aReferralCode() {
            return new ReferralCodeBuilder();
        }

        public ReferralCodeBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public ReferralCodeBuilder withValue(Integer value) {
            this.value = value;
            return this;
        }

        public ReferralCodeBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public ReferralCode build() {
            ReferralCode referralCode = new ReferralCode();
            referralCode.setCode(code);
            referralCode.setValue(value);
            referralCode.setUser(user);
            return referralCode;
        }
    }
}

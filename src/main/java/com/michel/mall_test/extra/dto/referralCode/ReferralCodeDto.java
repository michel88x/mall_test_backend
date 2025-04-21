package com.michel.mall_test.extra.dto.referralCode;

import com.michel.mall_test.entity.User;

public class ReferralCodeDto {
    private String code;
    private Integer value;
    private Long user;

    public ReferralCodeDto() {
    }

    public ReferralCodeDto(String code, Integer value, Long user) {
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }


    public static final class referralCodeDtoBuilder {
        private String code;
        private Integer value;
        private Long user;

        private referralCodeDtoBuilder() {
        }

        public static referralCodeDtoBuilder areferralCodeDto() {
            return new referralCodeDtoBuilder();
        }

        public referralCodeDtoBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public referralCodeDtoBuilder withValue(Integer value) {
            this.value = value;
            return this;
        }

        public referralCodeDtoBuilder withUser(Long user) {
            this.user = user;
            return this;
        }

        public ReferralCodeDto build() {
            ReferralCodeDto referralCodeDto = new ReferralCodeDto();
            referralCodeDto.setCode(code);
            referralCodeDto.setValue(value);
            referralCodeDto.setUser(user);
            return referralCodeDto;
        }
    }
}

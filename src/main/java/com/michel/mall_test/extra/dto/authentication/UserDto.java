package com.michel.mall_test.extra.dto.authentication;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.michel.mall_test.extra.enums.Gender;

import java.util.Date;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Gender gender;
    private Integer points;
    private Boolean emailVerified;
    private TokenDto token;

    public UserDto() {}

    public UserDto(Long id, String name, String email, String phoneNumber, Date birthDate, Gender gender, Integer points, Boolean emailVerified, TokenDto token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.points = points;
        this.emailVerified = emailVerified;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public TokenDto getToken() {
        return token;
    }

    public void setToken(TokenDto token) {
        this.token = token;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public static final class UserDtoBuilder {
        private Long id;
        private String name;
        private String email;
        private String phoneNumber;
        private Date birthDate;
        private Gender gender;
        private Integer points;
        private Boolean emailVerified;
        private TokenDto token;

        private UserDtoBuilder() {
        }

        public static UserDtoBuilder anUserDto() {
            return new UserDtoBuilder();
        }

        public UserDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDtoBuilder withBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserDtoBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public UserDtoBuilder withPoints(Integer points) {
            this.points = points;
            return this;
        }

        public UserDtoBuilder withToken(TokenDto token) {
            this.token = token;
            return this;
        }

        public UserDtoBuilder withEmailVerified(Boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.setId(id);
            userDto.setName(name);
            userDto.setEmail(email);
            userDto.setPhoneNumber(phoneNumber);
            userDto.setBirthDate(birthDate);
            userDto.setGender(gender);
            userDto.setPoints(points);
            userDto.setToken(token);
            userDto.setEmailVerified(emailVerified);
            return userDto;
        }
    }
}

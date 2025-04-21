package com.michel.mall_test.extra.dto.authentication;

public class TokenDto {
    private String token;
    private String refreshToken;

    public TokenDto() {}

    public TokenDto(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public static final class TokenDtoBuilder {
        private String token;
        private String refreshToken;

        private TokenDtoBuilder() {
        }

        public static TokenDtoBuilder aTokenDto() {
            return new TokenDtoBuilder();
        }

        public TokenDtoBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        public TokenDtoBuilder withRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public TokenDto build() {
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setRefreshToken(refreshToken);
            return tokenDto;
        }
    }
}

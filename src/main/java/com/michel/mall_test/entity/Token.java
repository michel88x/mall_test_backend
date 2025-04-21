package com.michel.mall_test.entity;

import com.michel.mall_test.extra.enums.TokenType;
import jakarta.persistence.*;

@Entity
public class Token extends BaseEntity{

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Token() {}

    public Token(String token, TokenType tokenType, boolean expired, boolean revoked, User user) {
        this.token = token;
        this.tokenType = tokenType;
        this.expired = expired;
        this.revoked = revoked;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static final class TokenBuilder {
        private String tokenValue;
        private TokenType tokenType;
        private boolean expired;
        private boolean revoked;
        private User user;

        private TokenBuilder() {
        }

        public static TokenBuilder aToken() {
            return new TokenBuilder();
        }

        public TokenBuilder withTokenValue(String tokenValue) {
            this.tokenValue = tokenValue;
            return this;
        }

        public TokenBuilder withTokenType(TokenType tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public TokenBuilder withExpired(boolean expired) {
            this.expired = expired;
            return this;
        }

        public TokenBuilder withRevoked(boolean revoked) {
            this.revoked = revoked;
            return this;
        }

        public TokenBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public Token build() {
            Token token = new Token();
            token.setToken(tokenValue);
            token.setTokenType(tokenType);
            token.setExpired(expired);
            token.setRevoked(revoked);
            token.setUser(user);
            return token;
        }
    }
}

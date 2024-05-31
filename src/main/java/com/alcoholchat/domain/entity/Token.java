package com.alcoholchat.domain.entity;

import com.alcoholchat.security.JWTUtil;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@Getter
@RedisHash("token")
public class Token implements Serializable {

    @Id
    private String email;

    private String refreshToken;

    @TimeToLive
    private Long expiration = JWTUtil.REFRESH_TOKEN_EXPIRED_TIME / 1000;

    public Token(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}

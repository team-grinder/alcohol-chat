package com.alcoholchat.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;

    // access 토큰 유효 시간: 1시간
    public static final Long ACCESS_TOKEN_EXPIRED_TIME = 1000L * 60 * 60;

    // refresh 토큰 유효 시간: 7일
    public static final Long REFRESH_TOKEN_EXPIRED_TIME = 1000L * 60 * 60 * 24 * 7;

    public JWTUtil(@Value("${jwt.secret}") String key) {
        secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmail(String token) {
        return getClaim(token).get("email", String.class);
    }


    public Boolean isExpired(String token) {
        return getClaim(token).getExpiration().before(new Date());
    }

    public String createJwt(String category, String email, Long expiredTime) {
        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .claim("category", category)
                .claim("email", email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(secretKey)
                .compact();
    }

    public String getCategory(String token) {

        return getClaim(token).get("category", String.class);
    }

    private Claims getClaim(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public Cookie createRefreshCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 24 * 7);
//        cookie.setSecure(true);
        cookie.setPath("/reissue");
        cookie.setHttpOnly(true);

        return cookie;
    }

}

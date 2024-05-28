package com.alcoholchat.security;

import com.alcoholchat.domain.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${jwt.secret}") String key) {
        secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmail(String token) {
        return getClaim(token).get("email", String.class);
    }

    public Role getRole(String token) {
        String roleString = getClaim(token).get("role", String.class);

        return Role.valueOf(roleString);
    }

    public Boolean isExpired(String token) {
        return getClaim(token).getExpiration().before(new Date());
    }

    public String createJwt(String email, Role role, Long expiredHours) {
        final Long EXPIRED_TIME = 1000 * 60 * 60 * expiredHours;
        return Jwts.builder()
                .claim("email", email)
                .claim("role", role.getValue())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                .signWith(secretKey)
                .compact();
    }

    private Claims getClaim(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }


}

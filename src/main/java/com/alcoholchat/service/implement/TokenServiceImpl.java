package com.alcoholchat.service.implement;

import com.alcoholchat.Exception.TokenException;
import com.alcoholchat.security.JWTUtil;
import com.alcoholchat.service.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JWTUtil jwtUtil;

    // todo. 레디스에 생성한 refresh 토큰 추가 + 기존 토큰 삭제
    @Override
    public String createRefreshToken(String refreshToken) {

        String email = jwtUtil.getEmail(refreshToken);

        return jwtUtil.createJwt("refresh", email, JWTUtil.REFRESH_TOKEN_EXPIRED_TIME);
    }

    @Override
    public String createAccessToken(String refreshToken) {

        String email = jwtUtil.getEmail(refreshToken);

        return jwtUtil.createJwt("access", email, JWTUtil.ACCESS_TOKEN_EXPIRED_TIME);
    }

    @Override
    public boolean compareTokenData(String accessToken, String refreshToken) {

        String accessEmail = jwtUtil.getEmail(accessToken);

        String refreshEmail = jwtUtil.getEmail(refreshToken);

        if (!accessEmail.equals(refreshEmail)) {

            throw new TokenException("토큰 정보가 일치하지 않음");

        } else {
            return true;
        }
    }

    // todo. 레디스에서 같은 토큰이 존재하는지 확인하는 구문 추가
    @Override
    public String getRefreshTokenFromCookies(HttpServletRequest request) {

        String refreshToken = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("refreshToken")) {

                refreshToken = cookie.getValue();
                break;
            }
        }

        if (refreshToken == null) {
            throw new TokenException("refresh token 이 존재하지 않음");
        }

        String category = jwtUtil.getCategory(refreshToken);

        if (!category.equals("refresh")) {

            throw new TokenException("refresh token 이 유효하지 않음");
        }

        return refreshToken;
    }

    @Override
    public String getAccessTokenFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {

            throw new TokenException("access token 이 존재하지 않음");
        }

        String accessToken = authorization.split(" ")[1];

        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {
            throw new TokenException("access token 이 유효하지 않음");
        }

        return accessToken;
    }

    @Override
    public HttpServletResponse reissue(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = getAccessTokenFromHeader(request);

        String refreshToken = getRefreshTokenFromCookies(request);

        compareTokenData(accessToken, refreshToken);

        String newAccessToken = createAccessToken(refreshToken);

        String newRefreshToken = createRefreshToken(refreshToken);

        response.setHeader("Authorization", "Bearer " + newAccessToken);

        response.addCookie(jwtUtil.createRefreshCookie("refreshToken", newRefreshToken));

        log.info("token reissue success");

        return response;
    }

}

package com.alcoholchat.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface TokenService {

    String createRefreshToken(String email);

    String createAccessToken(String email);

    boolean compareTokenData(String accessToken, String RefreshToken);

    String getRefreshTokenFromCookies(HttpServletRequest request);

    String getAccessTokenFromHeader(HttpServletRequest request);

    void validateRefreshToken(String refreshToken);

    HttpServletResponse reissue(HttpServletRequest request, HttpServletResponse response);

    Cookie createRefreshCookie(String refreshToken);
}

package com.alcoholchat.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface TokenService {

    String createRefreshToken(String refreshToken);

    String createAccessToken(String refreshToken);

    boolean compareTokenData(String accessToken, String RefreshToken);

    String getRefreshTokenFromCookies(HttpServletRequest request);

    String getAccessTokenFromHeader(HttpServletRequest request);

    HttpServletResponse reissue(HttpServletRequest request, HttpServletResponse response);
}

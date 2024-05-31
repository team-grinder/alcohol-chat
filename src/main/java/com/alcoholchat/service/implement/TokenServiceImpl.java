package com.alcoholchat.service.implement;

import com.alcoholchat.Exception.TokenException;
import com.alcoholchat.domain.entity.Token;
import com.alcoholchat.repository.TokenRepository;
import com.alcoholchat.security.JWTUtil;
import com.alcoholchat.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JWTUtil jwtUtil;

    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public String createRefreshToken(String email) {

        String newRefreshToken = jwtUtil.createJwt("refresh", email, JWTUtil.REFRESH_TOKEN_EXPIRED_TIME);

        // 레디스에 새로 발급한 refresh token 저장 (id가 같으므로 덮어씌워짐)
        Token token = tokenRepository.save(new Token(email, newRefreshToken));

        return newRefreshToken;
    }

    @Override
    public String createAccessToken(String email) {

        return jwtUtil.createJwt("access", email, JWTUtil.ACCESS_TOKEN_EXPIRED_TIME);
    }

    @Override
    public boolean compareTokenData(String accessToken, String refreshToken) {

        String accessEmail;

        try {

            accessEmail = jwtUtil.getEmail(accessToken);
        } catch (ExpiredJwtException e) {

            accessEmail = e.getClaims().get("email", String.class);

        }

        String refreshEmail = jwtUtil.getEmail(refreshToken);

        if (!accessEmail.equals(refreshEmail)) {

            throw new TokenException("토큰 정보가 일치하지 않음");

        } else {
            return true;
        }
    }

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

        return refreshToken;
    }

    @Override
    public String getAccessTokenFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        String accessToken = authorization.split(" ")[1];

        // 만료된 토큰 사용시 ExpiredJwtException 발생
        try {

            String category = jwtUtil.getCategory(accessToken);

        } catch (ExpiredJwtException e) {

            // access token의 category 확인
            String category = e.getClaims().get("category", String.class);


            if (!category.equals("access")) {
                throw new TokenException("access token 이 유효하지 않음");
            }
        }

        return accessToken;
    }

    @Override
    public void validateRefreshToken(String refreshToken) {

        String category = jwtUtil.getCategory(refreshToken);

        // category가 refresh인지 확인
        if (!category.equals("refresh")) {

            throw new TokenException("refresh token 의 category가 다름");
        }

        String email = jwtUtil.getEmail(refreshToken);

        // 레디스에서 email로 토큰 조회
        Optional<Token> savedToken = tokenRepository.findById(email);

        if (savedToken.isEmpty()) {

            throw new TokenException("서버에 토큰이 존재하지 않음");
        }

        String savedRefreshToken = savedToken.get().getRefreshToken();


        // 레디스의 토큰과 요청의 토큰이 일치하는지 확인
        if (!savedRefreshToken.equals(refreshToken)) {
            throw new TokenException("refresh token 이 유효하지 않습니다.");
        }

    }

    @Override
    @Transactional
    public HttpServletResponse reissue(HttpServletRequest request, HttpServletResponse response) {

        // access, refresh token 둘다 받아서 검증
        String accessToken = getAccessTokenFromHeader(request);

        String refreshToken = getRefreshTokenFromCookies(request);

        validateRefreshToken(refreshToken);

        compareTokenData(accessToken, refreshToken);

        String email = jwtUtil.getEmail(refreshToken);

        // 검증 후 두 개의 토큰 모두 재발급
        String newAccessToken = createAccessToken(email);

        String newRefreshToken = createRefreshToken(email);

        // access token은 헤더, refresh token은 쿠키에 담음
        response.setHeader("Authorization", "Bearer " + newAccessToken);

        response.addCookie(createRefreshCookie(newRefreshToken));

        log.info("token reissue success");

        return response;
    }

    @Override
    public Cookie createRefreshCookie(String refreshToken) {

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setMaxAge(60 * 60 * 24 * 7);
//        cookie.setSecure(true);
        cookie.setPath("/reissue");
        cookie.setHttpOnly(true);

        return cookie;

    }

}

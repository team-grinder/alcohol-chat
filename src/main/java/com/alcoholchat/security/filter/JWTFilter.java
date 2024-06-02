package com.alcoholchat.security.filter;

import com.alcoholchat.domain.entity.Member;
import com.alcoholchat.security.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        log.info("JWT filter start");

        if (authorization == null || !authorization.startsWith("Bearer ")) {

            log.error("access token error. Authorization: " + authorization);

            filterChain.doFilter(request, response);

            return;
        }

        String accessToken = authorization.split(" ")[1];

        try {

            jwtUtil.isExpired(accessToken);

        } catch (ExpiredJwtException e) {
            log.error(e.getMessage() + " access token expired");
//            request = new HttpServletRequestWrapper(request) {
//                @Override
//                public String getRequestURI() {
//                    return "/reissue";
//                }
//
//                @Override
//                public String getMethod() {
//                    return "POST";
//                }
//            };
//            filterChain.doFilter(request, response);

            response.setStatus(401);

            filterChain.doFilter(request, response);

            return;
        }

        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {

            response.getWriter().print("invalid access token");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        String email = jwtUtil.getEmail(accessToken);

        // null일경우 조치 필요
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}

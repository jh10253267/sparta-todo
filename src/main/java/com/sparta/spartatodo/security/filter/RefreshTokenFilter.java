package com.sparta.spartatodo.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartatodo.global.exception.RefreshTokenException;
import com.sparta.spartatodo.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {
    private final String refreshPath;
    private final JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if(!path.equals(refreshPath)) {
            log.info("skip refresh token filter...");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Refresh Token Filter...run...");

        Map<String, String> tokens = parseRequestJSON(request);

        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");

        try {
            checkAccessToken(accessToken);
        } catch(RefreshTokenException refreshTokenException) {
            refreshTokenException.sendResponseError(response);
            return;
        }
        Map<String, Object> refreshClaims = null;

        try {
            refreshClaims = checkRefreshToken(refreshToken);

            Integer exp = (Integer)refreshClaims.get("exp");
            Date expTime = new Date(Instant.ofEpochMilli(exp).toEpochMilli() * 1000);
            Date currenTime = new Date(System.currentTimeMillis());

            long gapTime = (expTime.getTime() - currenTime.getTime());

            String mid = (String)refreshClaims.get("mid");

            String accessTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 1);
            String refreshTokenValue = tokens.get("refreshToken");

            if(gapTime < (1000 * 60 * 60 * 24 * 3)) {
                refreshTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 30);
            }

        } catch (RefreshTokenException refreshTokenException) {
            refreshTokenException.sendResponseError(response);
            return;
        }
        sendTokens(accessToken, refreshToken, response);

    }

    private Map<String, String> parseRequestJSON(HttpServletRequest request) {
        try(Reader reader = new InputStreamReader(request.getInputStream())) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(reader, Map.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    private void checkAccessToken(String accessToken) throws RefreshTokenException {
        try {
            jwtUtil.validateToken(accessToken);
        } catch (ExpiredJwtException expiredJwtException) {
            log.info("Access Token has expired");
        } catch (Exception exception) {
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_ACCESS);
        }
    }
    private Map<String, Object> checkRefreshToken(String refreshToken) throws RefreshTokenException{
        try {
            Map<String, Object> values = jwtUtil.validateToken(refreshToken);
            return values;
        } catch (ExpiredJwtException expiredJwtException) {
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.OLD_REFRESH);
        } catch (MalformedJwtException malformedJwtException) {
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
        } catch (Exception e) {
            new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
        }
        return null;

    }
    private void sendTokens(String accessTokenValue, String refreshTokenValue, HttpServletResponse response) throws JsonProcessingException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(Map.of("accessToken", accessTokenValue, "refreshToken", refreshTokenValue));

        try {
            response.getWriter().println(jsonStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.sparta.spartatodo.util;

import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.MappedSuperclass;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Log4j2
class JWTUtilTest {

    @Autowired
    private JWTUtil jwtUtil;

    @DisplayName("JWTUtil의 토큰 생성 기능을 테스트")
    @Test
    void givenDummyClaimData_whenExecuteJwtGenerateTokenMethod_thenReturnsJwtString() {
        // Given
        Map<String, Object> claimMap = Map.of("mid", "usernametest");

        //When
        String jwtStr = jwtUtil.generateToken(claimMap, 1);

        //Then
        assertThat(jwtStr).isNotNull();
        log.info(jwtStr);
    }
    @DisplayName("유효기간이 지난 토큰을 이용해 JWTUtil의 validateToken 메소드가 잘 동작하는지 테스트")
    @Test
    void givenExpiredToken_whenDoValidateMethod_thenThrowsJwtException() {
        //Given
        String expiredJwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDAyNTkyNTksIm1pZCI6InVzZXJuYW1ldGVzdCIsImlhdCI6MTcwMDI1OTE5OX0.s2tJumR4xFCjVk1NyGj6c1MnpR3yPWYDfNh5CYye8CY";
        //When
        //Then
        assertThatCode(() -> jwtUtil.validateToken(expiredJwtStr))
                .isInstanceOf(JwtException.class);
    }

    @DisplayName("토큰 생성과 생성된 토큰을 검증한다")
    @Test
    void givenJwtString_whenValidateJwtStr_thenCheckTokenValue() {
        //Given
        String jwtStr = jwtUtil.generateToken(Map.of("mid","midtest", "mpw", "mpwtest"), 1);

        //When
        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

        //Then
        assertThat(claim.get("mid")).isEqualTo("midtest");
        assertThat(claim.get("mpw")).isEqualTo("mpwtest");
    }


}
package com.sparta.spartatodo.config;

import com.sparta.spartatodo.security.APIUserDetailsService;
import com.sparta.spartatodo.security.filter.APILoginFilter;
import com.sparta.spartatodo.security.filter.RefreshTokenFilter;
import com.sparta.spartatodo.security.filter.TokenCheckFilter;
import com.sparta.spartatodo.security.handler.APILoginSuccessHandler;
import com.sparta.spartatodo.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class CustomSecurityConfig {
    private final APIUserDetailsService apiUserDetailsService;
    private final JWTUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        log.info("----configure----");

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(apiUserDetailsService)
                .passwordEncoder(passwordEncoder());

        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.build();

        http.authenticationManager(authenticationManager);

        APILoginFilter apiLoginFilter = new APILoginFilter("/login");
        apiLoginFilter.setAuthenticationManager(authenticationManager);

        APILoginSuccessHandler apiLoginSuccessHandler = new APILoginSuccessHandler(jwtUtil);
        apiLoginFilter.setAuthenticationSuccessHandler(apiLoginSuccessHandler);

        http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(tokenCheckFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //TokenCheckFilter 호출
        http.addFilterBefore(new RefreshTokenFilter("/refreshToken", jwtUtil),
                TokenCheckFilter.class);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    private TokenCheckFilter tokenCheckFilter(JWTUtil jwtUtil) {
        return new TokenCheckFilter(jwtUtil);
    }
}

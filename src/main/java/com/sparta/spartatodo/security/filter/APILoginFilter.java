package com.sparta.spartatodo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

@Log4j2
public class APILoginFilter extends AbstractAuthenticationProcessingFilter {
    public APILoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("APILoginFilter-------------");

        if(request.getMethod().equalsIgnoreCase("GET")) {
            log.info("GET METHOD NOW ALLOWED");
            return null;
        }
        //사용자로 부터 입력받은 로그인 정보를 파싱
        Map<String, String> jsonData = parseRequestJSON(request);

        log.info(jsonData);

        //인증정보를 만들어 다음 필터로 넘겨줌
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                        jsonData.get("mid"),
                        jsonData.get("mpw"));

        return getAuthenticationManager().authenticate(authenticationToken);
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
}

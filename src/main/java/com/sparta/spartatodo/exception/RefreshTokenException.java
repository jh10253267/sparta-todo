package com.sparta.spartatodo.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class RefreshTokenException extends RuntimeException{
    private ErrorCase errorCase;

    public enum ErrorCase {
        NO_ACCESS,
        BAD_ACCESS,
        NO_REFRESH,
        OLD_REFRESH,
        BAD_REFRESH
    }

    public RefreshTokenException(ErrorCase errorCase) {
        super(errorCase.name());
        this.errorCase = errorCase;
    }
    public void sendResponseError(HttpServletResponse response) throws JsonProcessingException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();

        String responseStr = objectMapper.writeValueAsString(Map.of("message", errorCase.name(), "time", new Date()));

        try {
            response.getWriter().println(responseStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

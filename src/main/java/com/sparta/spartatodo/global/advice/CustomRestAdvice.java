package com.sparta.spartatodo.global.advice;


import com.sparta.spartatodo.global.Response;
import com.sparta.spartatodo.global.exception.CustomTodoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleNoSuchElement(Exception e) {
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("message", "No Such Element Exception");
        return ResponseEntity.badRequest().body(errorMap);
    }

    //유효성 검증에 실패했을 경우 에러 핸들링
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        if (e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });

        }

        return ResponseEntity.badRequest().body(errorMap);
    }
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ResponseEntity<Map<String, String>> handelRuntimeException(RuntimeException e) {
//        Map<String, String> errorMap = new HashMap<>();
//        errorMap.put("message", e.getMessage());
//        return ResponseEntity.badRequest().body(errorMap);
//    }
    @ExceptionHandler(CustomTodoException.class)
    public ResponseEntity<?> applicationHandler(CustomTodoException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }
}

package com.sparta.spartatodo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomTodoException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    public CustomTodoException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        return String.format("%s, %s", errorCode.getMessage(), message);
    }
}

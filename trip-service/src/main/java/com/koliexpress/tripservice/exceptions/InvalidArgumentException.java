package com.koliexpress.tripservice.exceptions;

import java.util.Map;

import lombok.Getter;

@Getter
public class InvalidArgumentException extends RuntimeException {
    private String field;
    private String message;
    public InvalidArgumentException(String message, String field) {
        super("Invalid argument: " + field + " - " + message);
        this.field = field;
        this.message = message;
    }

    public Map<String, String> getErrorMap() {
        return Map.of(field, message);
    }
}

package com.koliexpress.tripservice.exceptions.handlers;

import com.koliexpress.tripservice.dto.error.ValidationErrorResponseDto;
import com.koliexpress.tripservice.exceptions.InvalidArgumentException;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidArgumentException(InvalidArgumentException ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", 400);
        response.put("error", ex.getMessage());
        response.put("field", ex.getField());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fieldErrors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", 400);
        response.put("error", "Validation failed");
        response.put("errors", fieldErrors);

        return ResponseEntity.badRequest().body(response);
    }
}

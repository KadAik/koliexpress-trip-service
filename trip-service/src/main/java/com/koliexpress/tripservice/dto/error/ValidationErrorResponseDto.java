package com.koliexpress.tripservice.dto.error;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class ValidationErrorResponseDto {
    private final String field;
    private List<String> messages = new ArrayList<>();

    public ValidationErrorResponseDto(String field) {
        this.field = field;
    }

    public ValidationErrorResponseDto(String field, List<String> messages) {
        this.field = field;
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public Map<String, List<String>> getAsMap() {
        return Map.of(field, messages);
    }
}

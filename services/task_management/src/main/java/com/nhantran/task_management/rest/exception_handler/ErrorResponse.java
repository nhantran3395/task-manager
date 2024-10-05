package com.nhantran.task_management.rest.exception_handler;

import lombok.Builder;

import java.util.Map;

@Builder
public record ErrorResponse(
        String error,
        String message,
        Map<String, String> description
) {
}

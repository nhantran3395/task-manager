package com.nhantran.task_management.rest.exception_handler;

import lombok.Builder;

import java.time.Instant;
import java.util.Map;

@Builder
public record ErrorResponse(
        Instant timestamp,
        String error,
        String message,
        Map<String, String> description
) {
}

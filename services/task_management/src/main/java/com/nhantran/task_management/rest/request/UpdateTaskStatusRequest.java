package com.nhantran.task_management.rest.request;

import jakarta.validation.constraints.Pattern;

public record UpdateTaskStatusRequest(
        @Pattern(regexp = "^(next_state|put_on_hold|restore)$") String action
) {
}

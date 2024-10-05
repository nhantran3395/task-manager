package com.nhantran.task_management.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateTaskStatusRequest(
        @NotBlank(message = "action must not be empty")
        @Pattern(
                regexp = "^(next_state|put_on_hold|restore)$",
                message = "action must be one of next_state or put_on_hold or restore"
        ) String action
) {
}

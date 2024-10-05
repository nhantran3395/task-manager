package com.nhantran.task_management.rest.request;

import jakarta.validation.constraints.NotBlank;

public record AddNewTaskRequest(
        @NotBlank(message = "title must not be empty") String title,
        @NotBlank(message = "description must not be empty") String description,
        String thumbnailUrl
) {
}

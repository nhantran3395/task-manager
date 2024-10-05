package com.nhantran.task_management.rest.request;

import jakarta.validation.constraints.NotBlank;

public record AddNewTaskRequest(@NotBlank String title, @NotBlank String description, String thumbnailUrl) {
}

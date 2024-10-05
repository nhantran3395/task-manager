package com.nhantran.task_management.rest.request;

import jakarta.validation.constraints.NotBlank;

public record CreateNewBoardRequest(
        @NotBlank(message = "name must not be empty")String name,
        String iconSlug
) {
}

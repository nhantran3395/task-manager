package com.nhantran.task_management.rest.request;

import jakarta.validation.constraints.NotBlank;

public record CreateNewBoardRequest(@NotBlank String name, String iconSlug) {
}

package com.nhantran.task_management.rest.dto.command;

public record CreateNewBoardCommand(String name, String iconSlug, String externalUserId) {
}

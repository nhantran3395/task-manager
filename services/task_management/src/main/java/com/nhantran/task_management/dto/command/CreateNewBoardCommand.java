package com.nhantran.task_management.dto.command;

public record CreateNewBoardCommand(String name, String iconSlug, String externalUserId) {
}

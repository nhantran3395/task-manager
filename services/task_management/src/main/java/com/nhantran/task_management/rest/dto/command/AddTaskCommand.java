package com.nhantran.task_management.rest.dto.command;

public record AddTaskCommand(
        String title,
        String description,
        String thumbnailUrl,
        Long boardId,
        String externalUserId) {
}

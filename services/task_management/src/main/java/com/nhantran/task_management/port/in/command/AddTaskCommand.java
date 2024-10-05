package com.nhantran.task_management.port.in.command;

public record AddTaskCommand(
        String title,
        String description,
        String thumbnailUrl,
        Long boardId,
        String externalUserId) {
}

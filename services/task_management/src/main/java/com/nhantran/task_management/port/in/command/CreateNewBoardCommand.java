package com.nhantran.task_management.port.in.command;

public record CreateNewBoardCommand(String name, String iconSlug, String externalUserId) {
}
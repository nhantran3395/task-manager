package com.nhantran.task_management.port.in.command;

import com.nhantran.task_management.domain.model.UpdateStatusAction;

public record UpdateTaskStatusCommand(Long taskId, Long boardId, UpdateStatusAction action, String externalUserId) {
}

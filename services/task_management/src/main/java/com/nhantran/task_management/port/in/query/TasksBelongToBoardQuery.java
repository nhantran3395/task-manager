package com.nhantran.task_management.port.in.query;

public record TasksBelongToBoardQuery(Long boardId, String externalUserId) {
}

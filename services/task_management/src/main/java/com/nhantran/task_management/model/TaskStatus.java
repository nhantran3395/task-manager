package com.nhantran.task_management.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskStatus {
    NEW("new"),
    TODO("todo"),
    IN_PROGRESS("in_progress"),
    IN_REVIEW("in_review"),
    COMPLETED("completed");

    private final String value;
}

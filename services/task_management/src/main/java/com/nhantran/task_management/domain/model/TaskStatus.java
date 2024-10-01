package com.nhantran.task_management.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum TaskStatus {
    NEW("new"),
    TODO("todo"),
    IN_PROGRESS("in_progress"),
    IN_REVIEW("in_review"),
    COMPLETED("completed");

    private final String value;

    public static TaskStatus fromValue(String statusString) {
        return Stream.of(TaskStatus.values())
                .filter(status -> status.getValue().equals(statusString))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

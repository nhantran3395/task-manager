package com.nhantran.task_management.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum TaskStatus {
    NEW("new"),
    TODO("todo"),
    ON_HOLD("on_hold"),
    IN_PROGRESS("in_progress"),
    IN_REVIEW("in_review"),
    READY_FOR_TEST("ready_for_test"),
    IN_TESTING("in_testing"),
    COMPLETED("completed"),
    CANCELLED("cancelled");

    private final String value;

    public static TaskStatus fromValue(String statusString) {
        return Stream.of(TaskStatus.values())
                .filter(status -> status.getValue().equals(statusString))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static boolean isNewOrTodo(TaskStatus status) {
        return List.of(NEW, TODO).contains(status);
    }
}

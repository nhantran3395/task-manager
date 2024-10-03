package com.nhantran.task_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum UpdateStatusAction {
    NEXT_STATE("next_state"),
    PUT_ON_HOLD("put_on_hold"),
    RESTORE("restore"),
    CANCEL("cancel");

    private final String value;

    public static UpdateStatusAction fromValue(String actionString) {
        return Stream.of(UpdateStatusAction.values())
                .filter(status -> status.getValue().equals(actionString))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

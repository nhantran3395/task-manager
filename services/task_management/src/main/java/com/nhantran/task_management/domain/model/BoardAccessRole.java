package com.nhantran.task_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum BoardAccessRole {
    OWNER("owner"),
    MEMBER("member"),
    OBSERVER("observer"),
    GUEST("guest");

    private final String value;

    public static BoardAccessRole fromValue(String roleString) {
        return Stream.of(BoardAccessRole.values())
                .filter(role -> role.getValue().equals(roleString))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

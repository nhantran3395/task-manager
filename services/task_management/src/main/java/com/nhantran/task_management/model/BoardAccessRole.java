package com.nhantran.task_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardAccessRole {
    OWNER("owner"),
    MEMBER("member"),
    OBSERVER("observer"),
    GUEST("guest");

    private final String value;
}

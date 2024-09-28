package com.nhantran.task_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User {
    @Getter
    private Long id;
    private String externalId;
    @Getter
    private String name;
}

package com.nhantran.task_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String externalId;
    private String name;
}

package com.nhantran.task_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Board {
    private Long id;
    private String name;
    private String iconSlug;
    private List<Task> tasks;
}

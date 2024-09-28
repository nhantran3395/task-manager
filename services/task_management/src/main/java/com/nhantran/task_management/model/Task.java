package com.nhantran.task_management.model;

import lombok.Getter;

@Getter
public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private String thumbnailUrl;
    private Long boardId;
}

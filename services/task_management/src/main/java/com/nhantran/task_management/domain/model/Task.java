package com.nhantran.task_management.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private String thumbnailUrl;
    private Long boardId;

    public Task(String title, String description, String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.status = TaskStatus.NEW;
    }

    public Task(Long id, String title, String description, String thumbnailUrl, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.status = status;
    }
}

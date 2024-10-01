package com.nhantran.task_management.persistence.mapper;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;
import com.nhantran.task_management.persistence.entity.TaskJpaEntity;

public final class TaskMapper {
    public static Task toTask(TaskJpaEntity jpaEntity) {
        return new Task(
                jpaEntity.getId(),
                jpaEntity.getTitle(),
                jpaEntity.getDescription(),
                jpaEntity.getThumbnailUrl(),
                TaskStatus.fromValue(jpaEntity.getStatus())
        );
    }

    public static TaskJpaEntity toTaskJpaEntity(Task task) {
        return new TaskJpaEntity(
                task.getTitle(),
                task.getDescription(),
                task.getThumbnailUrl(),
                task.getStatus().getValue()
        );
    }
}

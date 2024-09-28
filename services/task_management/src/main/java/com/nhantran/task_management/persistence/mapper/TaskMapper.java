package com.nhantran.task_management.persistence.mapper;

import com.nhantran.task_management.model.Task;
import com.nhantran.task_management.persistence.entity.TaskJpaEntity;

public final class TaskMapper {
    public static Task toTask(TaskJpaEntity jpaEntity) {
        return new Task();
    }
}

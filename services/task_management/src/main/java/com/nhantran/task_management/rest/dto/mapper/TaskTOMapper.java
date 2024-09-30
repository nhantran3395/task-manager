package com.nhantran.task_management.rest.dto.mapper;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.rest.dto.to.TaskTO;

public final class TaskTOMapper {
    public static TaskTO toTaskTO(Task task){
        return  new TaskTO();
    }
}

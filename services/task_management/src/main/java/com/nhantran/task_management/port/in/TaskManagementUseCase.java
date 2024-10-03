package com.nhantran.task_management.port.in;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.rest.dto.command.AddTaskCommand;
import com.nhantran.task_management.rest.dto.command.UpdateTaskStatusCommand;
import com.nhantran.task_management.rest.dto.query.TasksBelongToBoardQuery;

import java.util.List;

public interface TaskManagementUseCase {
    List<Task> getTasksBelongToBoard(TasksBelongToBoardQuery query);
    Long addTask(AddTaskCommand addTaskCommand);
    void updateTaskStatus(UpdateTaskStatusCommand updateStatusCommand);
}

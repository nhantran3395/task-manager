package com.nhantran.task_management.port.in.use_case;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.port.in.command.AddTaskCommand;
import com.nhantran.task_management.port.in.command.UpdateTaskStatusCommand;
import com.nhantran.task_management.port.in.query.TasksBelongToBoardQuery;

import java.util.List;

public interface TaskManagementUseCase {
    List<Task> getTasksBelongToBoard(TasksBelongToBoardQuery query);
    Long addTask(AddTaskCommand addTaskCommand);
    void updateTaskStatus(UpdateTaskStatusCommand updateStatusCommand);
}

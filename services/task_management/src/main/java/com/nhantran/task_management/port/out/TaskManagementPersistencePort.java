package com.nhantran.task_management.port.out;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;

import java.util.List;

public interface TaskManagementPersistencePort {
    Long addTask(Task newTask, Board board);
    List<Task> getTasksBelongToBoard(Long boardId);
}

package com.nhantran.task_management.port.out;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskManagementPersistencePort {
    Optional<Task> findTask(Long taskId);
    Long addTask(Task newTask, Board board);
    List<Task> getTasksBelongToBoard(Long boardId);
    void updateTaskStatus(Task updatedTask);
}

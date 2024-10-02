package com.nhantran.task_management.domain.service;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.User;
import com.nhantran.task_management.exception.ResourceNotFoundException;
import com.nhantran.task_management.exception.RoleNotAllowedException;
import com.nhantran.task_management.exception.UserNotFoundException;
import com.nhantran.task_management.port.in.TaskManagementUseCase;
import com.nhantran.task_management.port.out.BoardManagementPersistencePort;
import com.nhantran.task_management.port.out.TaskManagementPersistencePort;
import com.nhantran.task_management.port.out.UserInfoPersistencePort;
import com.nhantran.task_management.rest.dto.command.AddTaskCommand;
import com.nhantran.task_management.rest.dto.query.TasksBelongToBoardQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskManagementService implements TaskManagementUseCase {
    private final BoardManagementPersistencePort boardManagementPersistencePort;
    private final UserInfoPersistencePort userInfoPersistencePort;
    private final TaskManagementPersistencePort taskManagementPersistencePort;

    @Override
    public List<Task> getTasksBelongToBoard(TasksBelongToBoardQuery query) {
        userInfoPersistencePort.findUser(query.externalUserId())
                .orElseThrow(UserNotFoundException::new);

        boardManagementPersistencePort.findBoard(query.boardId())
                .orElseThrow(ResourceNotFoundException::new);

        return taskManagementPersistencePort
                .getTasksBelongToBoard(query.boardId());
    }

    @Override
    public Long addTask(AddTaskCommand addTaskCommand) {
        User user = userInfoPersistencePort.findUser(addTaskCommand.externalUserId())
                .orElseThrow(UserNotFoundException::new);

        Board boardToAddTask = boardManagementPersistencePort.findBoard(addTaskCommand.boardId())
                .orElseThrow(ResourceNotFoundException::new);

        if(!boardToAddTask.userCanAddTask(user)) {
            throw new RoleNotAllowedException();
        }

        Task newTask = new Task(addTaskCommand.title(), addTaskCommand.description(), addTaskCommand.thumbnailUrl());

        return taskManagementPersistencePort.addTask(newTask, boardToAddTask);
    }
}

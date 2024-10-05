package com.nhantran.task_management.domain.service;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.User;
import com.nhantran.task_management.exception.ResourceNotBelongToParentException;
import com.nhantran.task_management.exception.ResourceNotFoundException;
import com.nhantran.task_management.exception.RoleNotAllowedException;
import com.nhantran.task_management.exception.UserNotFoundException;
import com.nhantran.task_management.port.in.use_case.TaskManagementUseCase;
import com.nhantran.task_management.port.out.BoardManagementPersistencePort;
import com.nhantran.task_management.port.out.TaskManagementPersistencePort;
import com.nhantran.task_management.port.out.UserInfoPersistencePort;
import com.nhantran.task_management.port.in.command.AddTaskCommand;
import com.nhantran.task_management.port.in.command.UpdateTaskStatusCommand;
import com.nhantran.task_management.port.in.query.TasksBelongToBoardQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
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
        log.debug("user {} add task to board {}",
                addTaskCommand.externalUserId(),
                addTaskCommand.boardId()
        );

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

    @Override
    public void updateTaskStatus(UpdateTaskStatusCommand updateStatusCommand) {
        log.debug("user {} perform action {} on task {}",
                updateStatusCommand.externalUserId(),
                updateStatusCommand.action(),
                updateStatusCommand.taskId()
        );

        User user = userInfoPersistencePort.findUser(updateStatusCommand.externalUserId())
                .orElseThrow(UserNotFoundException::new);

        Board board = boardManagementPersistencePort.findBoard(updateStatusCommand.boardId())
                .orElseThrow(ResourceNotFoundException::new);

        Task task = taskManagementPersistencePort.findTask(updateStatusCommand.taskId())
                .orElseThrow(ResourceNotFoundException::new);

        if(!board.userCanModifyTask(user)){
            throw new RoleNotAllowedException();
        }

        if(!task.isBelongTo(board.getId())){
            throw new ResourceNotBelongToParentException(
                    MessageFormat.format("Task {0} does not belong to board {1}", task.getId(), board.getId())
            );
        }

        task.performStateUpdateAction(updateStatusCommand.action());
        taskManagementPersistencePort.updateTaskStatus(task);
    }
}

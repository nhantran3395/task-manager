package com.nhantran.task_management.rest.controller;

import com.nhantran.task_management.domain.model.UpdateStatusAction;
import com.nhantran.task_management.port.in.use_case.TaskManagementUseCase;
import com.nhantran.task_management.port.in.command.AddTaskCommand;
import com.nhantran.task_management.port.in.command.UpdateTaskStatusCommand;
import com.nhantran.task_management.rest.dto.mapper.TaskTOMapper;
import com.nhantran.task_management.port.in.query.TasksBelongToBoardQuery;
import com.nhantran.task_management.rest.request.AddNewTaskRequest;
import com.nhantran.task_management.rest.request.UpdateTaskStatusRequest;
import com.nhantran.task_management.rest.dto.to.TaskTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class TaskManagementController {
    private final static String BASE_PATH = "/boards/{boardId}/tasks";
    private final TaskManagementUseCase taskManagementUseCase;

    @PostMapping(BASE_PATH)
    public ResponseEntity<Void> addTaskToBoard(
            @PathVariable("boardId") Long boardId,
            @RequestBody @Validated AddNewTaskRequest newTaskRequest,
            Principal principal
    ) {
        AddTaskCommand addTaskCommand = new AddTaskCommand(
                newTaskRequest.title(),
                newTaskRequest.description(),
                newTaskRequest.thumbnailUrl(),
                boardId,
                principal.getName()
        );
        Long newTaskId = taskManagementUseCase.addTask(addTaskCommand);
        URI newBoardLocation = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{taskId}")
                .buildAndExpand(newTaskId)
                .toUri();
        return ResponseEntity.created(newBoardLocation).build();
    }

    @GetMapping(BASE_PATH)
    public ResponseEntity<List<TaskTO>> getTasksBelongToBoard(
            @PathVariable("boardId") Long boardId,
            Principal principal
    ) {
        TasksBelongToBoardQuery query = new TasksBelongToBoardQuery(
                boardId,
                principal.getName()
        );
        List<TaskTO> tasks = taskManagementUseCase
                .getTasksBelongToBoard(query)
                .stream()
                .map(TaskTOMapper::toTaskTO)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping(BASE_PATH + "/{taskId}/state")
    public ResponseEntity<Void> updateTaskState(
            @PathVariable("boardId") Long boardId,
            @PathVariable("taskId") Long taskId,
            @RequestBody @Validated UpdateTaskStatusRequest request,
            Principal principal
    ) {
        UpdateTaskStatusCommand updateStatusCommand = new UpdateTaskStatusCommand(
                taskId,
                boardId,
                UpdateStatusAction.fromValue(request.action()),
                principal.getName()
        );
        taskManagementUseCase.updateTaskStatus(updateStatusCommand);
        return ResponseEntity.noContent().build();
    }
}

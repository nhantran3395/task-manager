package com.nhantran.task_management.rest.controller;

import com.nhantran.task_management.rest.dto.command.AddTaskCommand;
import com.nhantran.task_management.rest.dto.command.DeleteBoardCommand;
import com.nhantran.task_management.rest.dto.mapper.BoardTOMapper;
import com.nhantran.task_management.rest.dto.mapper.TaskTOMapper;
import com.nhantran.task_management.rest.dto.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.rest.dto.command.CreateNewBoardCommand;
import com.nhantran.task_management.rest.dto.query.TasksBelongToBoardQuery;
import com.nhantran.task_management.rest.dto.request.AddNewTaskRequest;
import com.nhantran.task_management.rest.dto.request.CreateNewBoardRequest;
import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.port.in.BoardManagementUseCase;
import com.nhantran.task_management.rest.dto.to.BoardTO;
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
@RequestMapping("/boards")
@AllArgsConstructor
public class BoardManagementController {
    private final BoardManagementUseCase boardManagementUseCase;

    @PostMapping()
    public ResponseEntity<Void> createNewBoard(
            @RequestBody @Validated CreateNewBoardRequest newBoardRequest,
            Principal principal
    ) {
        CreateNewBoardCommand newBoardCommand = new CreateNewBoardCommand(
                newBoardRequest.name(),
                newBoardRequest.iconSlug(),
                principal.getName()
        );
        Long newBoardId = boardManagementUseCase.createBoard(newBoardCommand);
        URI newBoardLocation = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{boardId}")
                .buildAndExpand(newBoardId)
                .toUri();
        return ResponseEntity.created(newBoardLocation).build();
    }

    @GetMapping()
    public ResponseEntity<List<BoardTO>> getBoardsViewableByUser(
            Principal principal
    ) {
        BoardsViewableByUserQuery query = new BoardsViewableByUserQuery(principal.getName());
        List<BoardTO> boards = boardManagementUseCase
                .getBoardsViewableByUser(query)
                .stream()
                .map(BoardTOMapper::toBoardTo)
                .toList();

        return ResponseEntity.ok(boards);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<List<Board>> deleteBoard(
            @PathVariable("boardId") Long boardId,
            Principal principal
    ) {
        DeleteBoardCommand deleteBoardCommand = new DeleteBoardCommand(boardId, principal.getName());
        boardManagementUseCase.deleteBoard(deleteBoardCommand);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{boardId}/tasks")
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
        Long newTaskId = boardManagementUseCase.addTask(addTaskCommand);
        URI newBoardLocation = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{taskId}")
                .buildAndExpand(newTaskId)
                .toUri();
        return ResponseEntity.created(newBoardLocation).build();
    }

    @GetMapping("/{boardId}/tasks")
    public ResponseEntity<List<TaskTO>> getTasksBelongToBoard(
            @PathVariable("boardId") Long boardId,
            Principal principal
    ) {
        TasksBelongToBoardQuery query = new TasksBelongToBoardQuery(
                boardId,
                principal.getName()
        );
        List<TaskTO> tasks = boardManagementUseCase
                .getTasksBelongToBoard(query)
                .stream()
                .map(TaskTOMapper::toTaskTO)
                .toList();
        return ResponseEntity.ok(tasks);
    }
}

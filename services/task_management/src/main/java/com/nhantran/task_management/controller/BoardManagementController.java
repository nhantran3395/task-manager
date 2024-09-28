package com.nhantran.task_management.controller;

import com.nhantran.task_management.dto.command.DeleteBoardCommand;
import com.nhantran.task_management.dto.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.dto.command.CreateNewBoardCommand;
import com.nhantran.task_management.dto.request.CreateNewBoardRequest;
import com.nhantran.task_management.model.Board;
import com.nhantran.task_management.service.BoardManagementUseCase;
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
    public ResponseEntity<List<Board>> getBoardsViewableByUser(
            Principal principal
    ) {
        BoardsViewableByUserQuery query = new BoardsViewableByUserQuery(principal.getName());
        List<Board> boards = boardManagementUseCase.getBoardsViewableByUser(query);
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
}

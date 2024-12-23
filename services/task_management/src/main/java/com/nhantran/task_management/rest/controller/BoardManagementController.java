package com.nhantran.task_management.rest.controller;

import com.nhantran.task_management.port.in.command.DeleteBoardCommand;
import com.nhantran.task_management.rest.dto.mapper.BoardTOMapper;
import com.nhantran.task_management.port.in.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.port.in.command.CreateNewBoardCommand;
import com.nhantran.task_management.rest.request.CreateNewBoardRequest;
import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.port.in.use_case.BoardManagementUseCase;
import com.nhantran.task_management.rest.dto.to.BoardTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/v0")
@AllArgsConstructor
@Tag(name = "Board")
public class BoardManagementController {
    private static final String BASE_PATH = "boards";
    private final BoardManagementUseCase boardManagementUseCase;

    @PostMapping(BASE_PATH)
    @Operation(summary = "Create a new board")
    public ResponseEntity<Void> createNewBoard(
            @RequestBody @Valid CreateNewBoardRequest newBoardRequest,
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

    @GetMapping(BASE_PATH)
    @Operation(summary = "Get list of boards")
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

    @DeleteMapping(BASE_PATH + "/{boardId}")
    @Operation(summary = "Delete a board")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    content = { @Content(mediaType = "application/json") }
            )
    })
    public ResponseEntity<List<Board>> deleteBoard(
            @PathVariable("boardId") Long boardId,
            Principal principal
    ) {
        DeleteBoardCommand deleteBoardCommand = new DeleteBoardCommand(boardId, principal.getName());
        boardManagementUseCase.deleteBoard(deleteBoardCommand);
        return ResponseEntity.noContent().build();
    }
}

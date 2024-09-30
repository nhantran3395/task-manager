package com.nhantran.task_management.service;

import com.nhantran.task_management.dto.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.dto.command.CreateNewBoardCommand;
import com.nhantran.task_management.dto.command.DeleteBoardCommand;
import com.nhantran.task_management.dto.query.TasksBelongToBoardQuery;
import com.nhantran.task_management.exception.ResourceNotFoundException;
import com.nhantran.task_management.exception.RoleNotAllowedException;
import com.nhantran.task_management.exception.UserNotFoundException;
import com.nhantran.task_management.model.*;
import com.nhantran.task_management.persistence.port.BoardManagementPersistencePort;
import com.nhantran.task_management.persistence.port.UserInfoPersistencePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class BoardManagementService implements BoardManagementUseCase{
    private BoardManagementPersistencePort boardManagementPersistencePort;
    private UserInfoPersistencePort userInfoPersistencePort;

    @Override
    public Long createBoard(CreateNewBoardCommand newBoardCommand) {
        User user = userInfoPersistencePort.findUser(newBoardCommand.externalUserId())
                .orElseThrow(UserNotFoundException::new);

        Board newBoard = new Board(newBoardCommand.name(), newBoardCommand.iconSlug());
        newBoard.addOwner(user);

        return boardManagementPersistencePort.createNewBoard(newBoard);
    }

    @Override
    public void deleteBoard(DeleteBoardCommand deleteBoardCommand) {
        log.debug("user {} deleting board {}", deleteBoardCommand.externalUserId(), deleteBoardCommand.id());

        User user = userInfoPersistencePort.findUser(deleteBoardCommand.externalUserId())
                .orElseThrow(UserNotFoundException::new);

        Board boardToDelete = boardManagementPersistencePort.findBoard(deleteBoardCommand.id())
                .orElseThrow(ResourceNotFoundException::new);

        if (!boardToDelete.userCanDelete(user)) {
            throw new RoleNotAllowedException();
        }

        boardManagementPersistencePort.deleteBoard(boardToDelete);
    }

    @Override
    public List<Board> getBoardsViewableByUser(BoardsViewableByUserQuery query) {
        User user = userInfoPersistencePort.findUser(query.externalUserId())
                .orElseThrow(UserNotFoundException::new);

        return boardManagementPersistencePort.getBoardsViewableByUser(user);
    }

    @Override
    public List<Task> getTasksBelongToBoard(TasksBelongToBoardQuery query) {
        return boardManagementPersistencePort.getTasksBelongToBoard(query.boardId());
    }
}

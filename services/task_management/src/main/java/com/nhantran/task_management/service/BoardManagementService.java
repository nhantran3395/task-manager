package com.nhantran.task_management.service;

import com.nhantran.task_management.dto.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.dto.command.CreateNewBoardCommand;
import com.nhantran.task_management.dto.command.DeleteBoardCommand;
import com.nhantran.task_management.dto.query.TasksBelongToBoardQuery;
import com.nhantran.task_management.exception.UserNotFoundException;
import com.nhantran.task_management.model.*;
import com.nhantran.task_management.persistence.port.BoardManagementPersistencePort;
import com.nhantran.task_management.persistence.port.UserInfoPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
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
            boardManagementPersistencePort.deleteBoard(
                    deleteBoardCommand.id(), deleteBoardCommand.externalUserId());
    }

    @Override
    public List<Board> getBoardsViewableByUser(BoardsViewableByUserQuery query) {
        return boardManagementPersistencePort.getBoardsViewableByUser(query.externalUserId());
    }

    @Override
    public List<Task> getTasksBelongToBoard(TasksBelongToBoardQuery query) {
        return boardManagementPersistencePort.getTasksBelongToBoard(query.boardId());
    }
}

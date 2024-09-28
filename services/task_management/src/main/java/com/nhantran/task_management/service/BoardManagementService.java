package com.nhantran.task_management.service;

import com.nhantran.task_management.dto.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.dto.command.CreateNewBoardCommand;
import com.nhantran.task_management.dto.command.DeleteBoardCommand;
import com.nhantran.task_management.dto.query.TasksBelongToBoardQuery;
import com.nhantran.task_management.model.Board;
import com.nhantran.task_management.model.Task;
import com.nhantran.task_management.persistence.port.BoardManagementPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BoardManagementService implements BoardManagementUseCase{
    private BoardManagementPersistencePort boardManagementPersistencePort;

    @Override
    public Long createBoard(CreateNewBoardCommand newBoardCommand) {
            return boardManagementPersistencePort.createNewBoard(
                    newBoardCommand.name(), newBoardCommand.iconSlug(), newBoardCommand.externalUserId()
            );
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

package com.nhantran.task_management.port.in.use_case;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.port.in.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.port.in.command.CreateNewBoardCommand;
import com.nhantran.task_management.port.in.command.DeleteBoardCommand;

import java.util.List;

public interface BoardManagementUseCase {
    Long createBoard(CreateNewBoardCommand newBoardCommand);
    void deleteBoard(DeleteBoardCommand deleteBoardCommand);
    List<Board> getBoardsViewableByUser(BoardsViewableByUserQuery query);
}

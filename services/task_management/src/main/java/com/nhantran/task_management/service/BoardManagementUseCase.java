package com.nhantran.task_management.service;

import com.nhantran.task_management.dto.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.dto.command.CreateNewBoardCommand;
import com.nhantran.task_management.dto.command.DeleteBoardCommand;
import com.nhantran.task_management.dto.query.TasksBelongToBoardQuery;
import com.nhantran.task_management.model.Board;
import com.nhantran.task_management.model.Task;

import java.util.List;

public interface BoardManagementUseCase {
    Long createBoard(CreateNewBoardCommand newBoardCommand);
    void deleteBoard(DeleteBoardCommand deleteBoardCommand);
    List<Board> getBoardsViewableByUser(BoardsViewableByUserQuery query);
    List<Task> getTasksBelongToBoard(TasksBelongToBoardQuery query);
}

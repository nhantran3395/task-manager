package com.nhantran.task_management.port.in;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.rest.dto.command.AddTaskCommand;
import com.nhantran.task_management.rest.dto.query.BoardsViewableByUserQuery;
import com.nhantran.task_management.rest.dto.command.CreateNewBoardCommand;
import com.nhantran.task_management.rest.dto.command.DeleteBoardCommand;
import com.nhantran.task_management.rest.dto.query.TasksBelongToBoardQuery;

import java.util.List;

public interface BoardManagementUseCase {
    Long createBoard(CreateNewBoardCommand newBoardCommand);
    void deleteBoard(DeleteBoardCommand deleteBoardCommand);
    List<Board> getBoardsViewableByUser(BoardsViewableByUserQuery query);
    List<Task> getTasksBelongToBoard(TasksBelongToBoardQuery query);
    Long addTask(AddTaskCommand addTaskCommand);
}

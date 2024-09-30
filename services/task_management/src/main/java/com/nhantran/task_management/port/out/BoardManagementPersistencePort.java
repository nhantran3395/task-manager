package com.nhantran.task_management.port.out;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface BoardManagementPersistencePort {
    Optional<Board> findBoard(Long boardId);
    Long createNewBoard(Board boardToCreate);
    void deleteBoard(Board boardToDelete);
    List<Board> getBoardsViewableByUser(User user);
    List<Task> getTasksBelongToBoard(Long boardId);
}

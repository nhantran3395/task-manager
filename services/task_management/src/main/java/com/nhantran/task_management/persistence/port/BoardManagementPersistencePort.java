package com.nhantran.task_management.persistence.port;

import com.nhantran.task_management.model.Board;
import com.nhantran.task_management.model.Task;
import com.nhantran.task_management.model.User;

import java.util.List;
import java.util.Optional;

public interface BoardManagementPersistencePort {
    Optional<Board> findBoard(Long boardId);
    Long createNewBoard(Board boardToCreate);
    void deleteBoard(Board boardToDelete);
    List<Board> getBoardsViewableByUser(User user);
    List<Task> getTasksBelongToBoard(Long boardId);
}

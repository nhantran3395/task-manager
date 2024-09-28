package com.nhantran.task_management.persistence.port;

import com.nhantran.task_management.model.Board;
import com.nhantran.task_management.model.Task;

import java.util.List;

public interface BoardManagementPersistencePort {
    Long createNewBoard(String name, String iconSlug, String externalUserId);
    void deleteBoard(Long boardId, String externalUserid);
    List<Board> getBoardsViewableByUser(String externalUserId);
    List<Task> getTasksBelongToBoard(Long boardId);
}

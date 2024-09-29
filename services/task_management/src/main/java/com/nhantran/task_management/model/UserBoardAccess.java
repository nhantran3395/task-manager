package com.nhantran.task_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserBoardAccess {
    private Long boardId;
    private Long userId;
    private User user;
    private BoardAccessRole role;

    public UserBoardAccess(Board board, User user, BoardAccessRole role) {
        this.userId = user.getId();
        this.user = user;
        this.boardId = board.getId();
        this.role = role;
    }
}

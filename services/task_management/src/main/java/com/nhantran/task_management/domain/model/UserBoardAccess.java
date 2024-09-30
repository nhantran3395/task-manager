package com.nhantran.task_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserBoardAccess {
    private Long boardId;
    private Long userId;
    private BoardAccessRole role;
}

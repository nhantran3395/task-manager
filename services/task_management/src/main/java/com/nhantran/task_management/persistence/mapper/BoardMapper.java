package com.nhantran.task_management.persistence.mapper;

import com.nhantran.task_management.model.Board;
import com.nhantran.task_management.persistence.entity.BoardJpaEntity;
import com.nhantran.task_management.persistence.entity.UserBoardJpaEntity;

import java.util.Set;
import java.util.stream.Collectors;

public final class BoardMapper {
    public static Board toBoard(BoardJpaEntity boardJpaEntity) {
        return new Board(
                boardJpaEntity.getId(),
                boardJpaEntity.getName(),
                boardJpaEntity.getIconSlug(),
                boardJpaEntity.getTasks().stream().map(TaskMapper::toTask).collect(Collectors.toList()),
                boardJpaEntity.getUsers().stream().map(UserBoardAccessMapper::toUserBoardAccess).collect(Collectors.toSet())
        );
    }

    public static BoardJpaEntity toBoardJpaEntity(Board board) {
        BoardJpaEntity boardJpaEntity = new BoardJpaEntity(
                board.getName(), board.getIconSlug());

        Set<UserBoardJpaEntity> userBoardJpaEntities = board.getAccesses().stream()
                .map(userBoardAccess -> UserBoardAccessMapper.toUserBoardJpaEntity(boardJpaEntity, userBoardAccess))
                .collect(Collectors.toSet());
        boardJpaEntity.setUsers(userBoardJpaEntities);
        return boardJpaEntity;
    }
}

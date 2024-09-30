package com.nhantran.task_management.persistence.mapper;

import com.nhantran.task_management.model.BoardAccessRole;
import com.nhantran.task_management.model.UserBoardAccess;
import com.nhantran.task_management.persistence.entity.BoardJpaEntity;
import com.nhantran.task_management.persistence.entity.UserBoardAccessJpaEntity;

public final class UserBoardAccessMapper {
    public static UserBoardAccess toUserBoardAccess(UserBoardAccessJpaEntity userBoardAccessJpaEntity) {
        return new UserBoardAccess(
                userBoardAccessJpaEntity.getAccessId().getBoardId(),
                userBoardAccessJpaEntity.getAccessId().getUserId(),
                BoardAccessRole.valueOf(userBoardAccessJpaEntity.getAccessRole())
        );
    }

    public static UserBoardAccessJpaEntity toUserBoardJpaEntity(BoardJpaEntity boardJpaEntity, UserBoardAccess userBoardAccess) {
        UserBoardAccessJpaEntity userBoardAccessJpaEntity = new UserBoardAccessJpaEntity(
                userBoardAccess.getUserId(),
                boardJpaEntity,
                userBoardAccess.getRole().getValue()
        );

        boardJpaEntity.addOwner(userBoardAccess.getUserId());
        return userBoardAccessJpaEntity;
    }
}
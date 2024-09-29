package com.nhantran.task_management.persistence.mapper;

import com.nhantran.task_management.model.BoardAccessRole;
import com.nhantran.task_management.model.UserBoardAccess;
import com.nhantran.task_management.persistence.entity.BoardJpaEntity;
import com.nhantran.task_management.persistence.entity.UserBoardJpaEntity;
import com.nhantran.task_management.persistence.entity.UserJpaEntity;

public final class UserBoardAccessMapper {
    public static UserBoardAccess toUserBoardAccess(UserBoardJpaEntity userBoardJpaEntity) {
        return new UserBoardAccess(
                BoardMapper.toBoard(userBoardJpaEntity.getBoard()),
                UserMapper.toUser(userBoardJpaEntity.getUser()),
                BoardAccessRole.valueOf(userBoardJpaEntity.getAccessRole())
        );
    }

    public static UserBoardJpaEntity toUserBoardJpaEntity(BoardJpaEntity boardJpaEntity, UserBoardAccess userBoardAccess) {
        UserJpaEntity userJpaEntity = UserMapper.toUserJpaEntity(userBoardAccess.getUser());

        UserBoardJpaEntity userBoardJpaEntity = new UserBoardJpaEntity(
                userJpaEntity,
                boardJpaEntity,
                String.valueOf(userBoardAccess.getRole())
        );

        boardJpaEntity.addOwner(userBoardJpaEntity.getUser());
        userJpaEntity.registerBoardAccess(userBoardJpaEntity);
        return userBoardJpaEntity;
    }
}
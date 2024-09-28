package com.nhantran.task_management.persistence.mapper;

import com.nhantran.task_management.model.User;
import com.nhantran.task_management.persistence.entity.UserJpaEntity;

public final class UserMapper {
    public static User toUser(UserJpaEntity jpaEntity) {
        return new User(
                jpaEntity.getId(),
                jpaEntity.getExternalId(),
                jpaEntity.getName()
        );
    }
}

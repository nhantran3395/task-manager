package com.nhantran.task_management.port.out;

import com.nhantran.task_management.domain.model.User;

import java.util.Optional;

public interface UserInfoPersistencePort {
    Optional<User> findUser(String externalId);
}

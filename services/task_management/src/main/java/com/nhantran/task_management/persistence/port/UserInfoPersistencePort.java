package com.nhantran.task_management.persistence.port;

import com.nhantran.task_management.model.User;

import java.util.Optional;

public interface UserInfoPersistencePort {
    Optional<User> findUser(String externalId);
}

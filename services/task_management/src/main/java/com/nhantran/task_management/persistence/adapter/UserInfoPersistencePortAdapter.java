package com.nhantran.task_management.persistence.adapter;

import com.nhantran.task_management.domain.model.User;
import com.nhantran.task_management.persistence.mapper.UserMapper;
import com.nhantran.task_management.port.out.UserInfoPersistencePort;
import com.nhantran.task_management.persistence.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserInfoPersistencePortAdapter implements UserInfoPersistencePort {
    private final UserJpaRepository userRepository;

    @Override
    @Cacheable("users")
    public Optional<User> findUser(String externalId) {
        return userRepository.findByExternalId(externalId)
                .map(UserMapper::toUser);
    }
}

package com.nhantran.task_management.persistence.repository;

import com.nhantran.task_management.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByExternalId(String externalId);
}

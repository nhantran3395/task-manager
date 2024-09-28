package com.nhantran.task_management.persistence.repository;

import com.nhantran.task_management.persistence.entity.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, Long> {
}

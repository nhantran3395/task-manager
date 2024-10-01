package com.nhantran.task_management.persistence.repository;

import com.nhantran.task_management.persistence.entity.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, Long> {
    List<TaskJpaEntity> findByBoardId(Long boardId);
}

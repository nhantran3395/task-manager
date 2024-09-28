package com.nhantran.task_management.persistence.repository;

import com.nhantran.task_management.persistence.entity.BoardJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<BoardJpaEntity, Long> {
    @Query("SELECT b FROM BoardJpaEntity b JOIN b.users ub WHERE ub.user.id = :userId")
    List<BoardJpaEntity> findByUserId(@Param("userId") Long userId);
}

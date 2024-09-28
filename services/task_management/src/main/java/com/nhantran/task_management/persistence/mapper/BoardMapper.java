package com.nhantran.task_management.persistence.mapper;

import com.nhantran.task_management.model.Board;
import com.nhantran.task_management.persistence.entity.BoardJpaEntity;

import java.util.stream.Collectors;

public final class BoardMapper {
    public static Board toBoard(BoardJpaEntity jpaEntity) {
        return new Board(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getIconSlug(),
                jpaEntity.getTasks().stream().map(TaskMapper::toTask).collect(Collectors.toList())
        );
    }
}

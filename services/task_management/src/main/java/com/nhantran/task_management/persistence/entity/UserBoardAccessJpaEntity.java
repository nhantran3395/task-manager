package com.nhantran.task_management.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_boards")
@Getter
@Setter
@NoArgsConstructor
public class UserBoardJpaEntity {
    @EmbeddedId
    private UserBoardId accessId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardId")
    private BoardJpaEntity board;

    @Column(name = "role")
    private String accessRole;

    public UserBoardJpaEntity(Long userId, BoardJpaEntity boardEntity, String accessRole) {
        this.accessId = new UserBoardId(userId, boardEntity.getId());
        this.board = boardEntity;
        this.accessRole = accessRole;
    }
}

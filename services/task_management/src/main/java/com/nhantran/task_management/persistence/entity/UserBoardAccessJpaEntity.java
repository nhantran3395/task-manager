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
public class UserBoardAccessJpaEntity {
    @EmbeddedId
    private UserBoardAccessId accessId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardId")
    private BoardJpaEntity board;

    @Column(name = "role")
    private String accessRole;

    public UserBoardAccessJpaEntity(Long userId, BoardJpaEntity boardEntity, String accessRole) {
        this.accessId = new UserBoardAccessId(userId, boardEntity.getId());
        this.board = boardEntity;
        this.accessRole = accessRole;
    }
}

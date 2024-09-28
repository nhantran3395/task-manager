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
    private UserBoardId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardId")
    private BoardJpaEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserJpaEntity user;

    @Column(name = "role")
    private String accessRole;

    public UserBoardJpaEntity(UserJpaEntity userEntity, BoardJpaEntity boardEntity, String accessRole) {
        this.id = new UserBoardId(userEntity.getId(), boardEntity.getId());
        this.user = userEntity;
        this.board = boardEntity;
        this.accessRole = accessRole;
    }
}

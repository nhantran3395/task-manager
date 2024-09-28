package com.nhantran.task_management.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserBoardId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "board_id")
    private Long boardId;
}

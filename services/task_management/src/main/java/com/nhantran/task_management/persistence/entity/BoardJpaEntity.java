package com.nhantran.task_management.persistence.entity;

import com.nhantran.task_management.domain.model.BoardAccessRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "boards")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class BoardJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon_slug")
    private String iconSlug;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updatedAt;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskJpaEntity> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserBoardAccessJpaEntity> accesses = new HashSet<>();

    public BoardJpaEntity(String name, String iconSlug) {
        this.name = name;
        this.iconSlug = iconSlug;
    }

    public void addOwner(Long userId) {
        UserBoardAccessJpaEntity userBoardAccessEntity = new UserBoardAccessJpaEntity(
                userId, this, BoardAccessRole.OWNER.getValue());
        accesses.add(userBoardAccessEntity);
    }
}

package com.nhantran.task_management.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Board {
    private Long id;
    private String name;
    private String iconSlug;
    private List<Task> tasks = new ArrayList<>();
    private Set<UserBoardAccess> accesses = new HashSet<>();

    public Board(String name, String iconSlug) {
        this.name = name;
        this.iconSlug = iconSlug;
    }

    public Board(Long id, String name, String iconSlug, Set<UserBoardAccess> accesses) {
        this.id = id;
        this.name = name;
        this.iconSlug = iconSlug;
        this.accesses = accesses;
    }

    public void addOwner(User user) {
        UserBoardAccess ownerAccess = new UserBoardAccess(this.id, user.getId(), BoardAccessRole.OWNER);
        accesses.add(ownerAccess);
    }

    public boolean userCanDelete(User user) {
        return accesses
                .stream()
                .anyMatch(access ->
                        access.getUserId().equals(user.getId()) &&
                                access.getRole().equals(BoardAccessRole.OWNER)
                );
    }

    public boolean userCanAddTask(User user) {
        return accesses
                .stream()
                .anyMatch(access ->
                        access.getUserId().equals(user.getId()) &&
                                List.of(BoardAccessRole.OWNER, BoardAccessRole.MEMBER).contains(access.getRole())
                );
    }

    public boolean userCanModifyTask(User user) {
        return accesses
                .stream()
                .anyMatch(access ->
                        access.getUserId().equals(user.getId()) &&
                                List.of(BoardAccessRole.OWNER, BoardAccessRole.MEMBER).contains(access.getRole())
                );
    }
}

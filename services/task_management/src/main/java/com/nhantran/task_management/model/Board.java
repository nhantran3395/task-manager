package com.nhantran.task_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class Board {
    private Long id;
    private String name;
    private String iconSlug;
    private List<Task> tasks;
    private Set<UserBoardAccess> accesses;

    public Board(String name, String iconSlug) {
        this.name = name;
        this.iconSlug = iconSlug;
        this.tasks = new ArrayList<>();
        this.accesses = new HashSet<>();
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
}

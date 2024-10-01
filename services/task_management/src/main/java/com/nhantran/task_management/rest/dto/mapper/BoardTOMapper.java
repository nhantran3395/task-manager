package com.nhantran.task_management.rest.dto.mapper;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.rest.dto.to.BoardTO;

public final class BoardTOMapper {
    public static BoardTO toBoardTo(Board board) {
        return new BoardTO(
                board.getId(),
                board.getName(),
                board.getIconSlug(),
                board.getAccesses().stream()
                        .filter(access -> access.getBoardId().equals(board.getId())).toList()
                        .getFirst().getRole().getValue()
        );
    }
}

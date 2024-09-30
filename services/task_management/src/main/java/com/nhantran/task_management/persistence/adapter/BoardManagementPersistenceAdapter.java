package com.nhantran.task_management.persistence.adapter;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.User;
import com.nhantran.task_management.persistence.mapper.BoardMapper;
import com.nhantran.task_management.port.out.BoardManagementPersistencePort;
import com.nhantran.task_management.persistence.entity.BoardJpaEntity;
import com.nhantran.task_management.persistence.repository.BoardJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BoardManagementPersistenceAdapter implements BoardManagementPersistencePort {
    private final BoardJpaRepository boardRepository;

    @Override
    public Optional<Board>  findBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .map(BoardMapper::toBoard);
    }

    @Override
    public Long createNewBoard(Board boardToCreate) {
        BoardJpaEntity boardEntity = BoardMapper.toBoardJpaEntity(boardToCreate);
        BoardJpaEntity createdBoardEntity = boardRepository.save(boardEntity);
        return createdBoardEntity.getId();
    }

    @Override
    public void deleteBoard(Board boardToDelete) {
        boardRepository.deleteById(boardToDelete.getId());
    }

    @Override
    public List<Board> getBoardsViewableByUser(User user) {
        return boardRepository.findByUserId(user.getId())
                .stream().map(BoardMapper::toBoard).collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksBelongToBoard(Long boardId) {
        return null;
    }
}
package com.nhantran.task_management.persistence.adapter;

import com.nhantran.task_management.exception.ResourceNotFoundException;
import com.nhantran.task_management.model.Board;
import com.nhantran.task_management.model.Task;
import com.nhantran.task_management.persistence.mapper.BoardMapper;
import com.nhantran.task_management.persistence.port.BoardManagementPersistencePort;
import com.nhantran.task_management.persistence.entity.BoardJpaEntity;
import com.nhantran.task_management.persistence.entity.UserJpaEntity;
import com.nhantran.task_management.persistence.repository.BoardJpaRepository;
import com.nhantran.task_management.persistence.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BoardManagementPersistenceAdapter implements BoardManagementPersistencePort {
    private final BoardJpaRepository boardRepository;
    private final UserJpaRepository userRepository;

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
    public List<Board> getBoardsViewableByUser(String externalUserid) {
        UserJpaEntity userJpaEntity = findUser(externalUserid);

        return boardRepository.findByUserId(userJpaEntity.getId())
                .stream().map(BoardMapper::toBoard).collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksBelongToBoard(Long boardId) {
        return null;
    }

    private UserJpaEntity findUser(String externalUserId) {
        Optional<UserJpaEntity> maybeUser = userRepository.findByExternalId(externalUserId);
        if (maybeUser.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return maybeUser.get();
    }
}
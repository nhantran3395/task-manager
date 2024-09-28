package com.nhantran.task_management.persistence.adapter;

import com.nhantran.task_management.exception.RoleNotAllowedException;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class BoardManagementPersistenceAdapter implements BoardManagementPersistencePort {
    private final BoardJpaRepository boardRepository;
    private final UserJpaRepository userRepository;

    @Override
    public Long createNewBoard(String name, String iconSlug, String externalUserId) {
        UserJpaEntity userEntity = findUser(externalUserId);

        BoardJpaEntity newBoardEntity = new BoardJpaEntity(name, iconSlug);
        newBoardEntity.addOwner(userEntity);

        BoardJpaEntity createdBoardEntity = boardRepository.save(newBoardEntity);
        return createdBoardEntity.getId();
    }

    @Override
    public void deleteBoard(Long boardId, String externalUserId) {
        log.info("user {} deleting board {}", externalUserId, boardId);

        Optional<BoardJpaEntity> maybeBoard = boardRepository.findById(boardId);
        if (maybeBoard.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        UserJpaEntity userEntity = findUser(externalUserId);
        BoardJpaEntity board = maybeBoard.get();

        if(!board.userCanDelete(userEntity)) {
            throw new RoleNotAllowedException();
        }

        boardRepository.delete(board);
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

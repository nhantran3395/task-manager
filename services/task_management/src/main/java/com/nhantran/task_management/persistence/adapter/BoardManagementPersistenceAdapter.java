package com.nhantran.task_management.persistence.adapter;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.User;
import com.nhantran.task_management.persistence.entity.TaskJpaEntity;
import com.nhantran.task_management.persistence.mapper.BoardMapper;
import com.nhantran.task_management.persistence.mapper.TaskMapper;
import com.nhantran.task_management.persistence.repository.TaskJpaRepository;
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
    private final TaskJpaRepository taskRepository;

    @Override
    public Optional<Board>  findBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .map(BoardMapper::toBoard);
    }

    @Override
    public Long createNewBoard(Board boardToCreate) {
        BoardJpaEntity boardEntity = BoardMapper.createNewBoardJpaEntity(boardToCreate);
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
    public Long addTask(Task newTask, Board board) {
        BoardJpaEntity boardEntity = findEntity(board.getId());
        TaskJpaEntity taskEntity = TaskMapper.toTaskJpaEntity(newTask);
        taskEntity.setBoard(boardEntity);

        TaskJpaEntity savedTaskEntity = taskRepository.save(taskEntity);
        return savedTaskEntity.getId();
    }

    @Override
    public List<Task> getTasksBelongToBoard(Long boardId) {
        return taskRepository.findByBoardId(boardId)
                .stream()
                .map(TaskMapper::toTask)
                .toList();
    }

    private BoardJpaEntity findEntity(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(IllegalStateException::new);
    }
}
package com.nhantran.task_management.persistence.adapter;

import com.nhantran.task_management.domain.model.Board;
import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.persistence.entity.BoardJpaEntity;
import com.nhantran.task_management.persistence.entity.TaskJpaEntity;
import com.nhantran.task_management.persistence.mapper.TaskMapper;
import com.nhantran.task_management.persistence.repository.BoardJpaRepository;
import com.nhantran.task_management.persistence.repository.TaskJpaRepository;
import com.nhantran.task_management.port.out.TaskManagementPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TaskManagementPersistenceAdapter implements TaskManagementPersistencePort {
    private final BoardJpaRepository boardRepository;
    private final TaskJpaRepository taskRepository;

    @Override
    public Optional<Task> findTask(Long taskId) {
        return taskRepository
                .findById(taskId)
                .map(TaskMapper::toTask);
    }

    @Override
    public Long addTask(Task newTask, Board board) {
        BoardJpaEntity boardEntity = findBoardEntity(board.getId());
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

    @Override
    public void updateTaskStatus(Task updatedTask) {
        TaskJpaEntity taskEntity = findTaskEntity(updatedTask.getId());
        taskEntity.setStatus(updatedTask.getStatus().getValue());

        taskRepository.save(taskEntity);
    }

    private BoardJpaEntity findBoardEntity(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(IllegalStateException::new);
    }

    private TaskJpaEntity findTaskEntity(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(IllegalStateException::new);
    }
}

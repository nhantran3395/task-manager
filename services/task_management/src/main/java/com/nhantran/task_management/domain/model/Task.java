package com.nhantran.task_management.domain.model;

import com.nhantran.task_management.domain.model.task_state.NewState;
import com.nhantran.task_management.domain.model.task_state.TaskState;
import com.nhantran.task_management.domain.model.task_state.TaskStateFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
public class Task {
    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private String thumbnailUrl;

    private TaskState state;
    private TaskState prevState;
    private Long boardId;

    public Task(String title, String description, String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.state = new NewState(this);
    }

    public Task(
            Long id,
            String title,
            String description,
            String thumbnailUrl,
            TaskStatus status,
            TaskStatus prevStatus
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.state = TaskStateFactory.fromStatus(status, this);
        this.prevState = !Objects.isNull(prevStatus) ?
                TaskStateFactory.fromStatus(prevStatus, this) : null;
    }

    public void performStateUpdateAction(UpdateStatusAction action) {
        switch (action) {
            case NEXT_STATE -> state.next();
            case PUT_ON_HOLD -> state.hold();
            case RESTORE -> state.restore();
            case CANCEL -> state.cancel();
        }
    }

    public void changeState(TaskState nextState) {
        this.prevState = state;
        this.state = nextState;
    }

    public TaskStatus getStatus() {
        return state.getStatusRepresentative();
    }

    public Optional<TaskStatus> getPrevStatus() {
        return Optional.ofNullable(prevState)
                .map(TaskState::getStatusRepresentative);
    }

    public Optional<TaskState> getPrevState() {
        return Optional.ofNullable(prevState);
    }
}

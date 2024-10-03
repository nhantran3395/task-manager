package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

import java.util.Optional;

public class PendingState extends AbstractTaskState {
    public PendingState(Task task) {
        super(task, TaskStatus.ON_HOLD);
    }

    @Override
    public void hold() {
        // should do nothing since task is already pending
    }

    public void restore() {
        Optional<TaskState> maybePrevState = task
                .getPrevState();

        if(maybePrevState.isEmpty()) {
            changeState(TaskStateFactory.ofNew(task));
            return;
        }

        TaskState prevState = maybePrevState.get();
        if (TaskStatus.isNewOrTodo(prevState.getStatusRepresentative())) {
            changeState(TaskStateFactory.ofNew(task));
            return;
        }

        changeState(TaskStateFactory.ofInProgress(task));
    }

    @Override
    public void next() {
        // should do nothing while task being put on hold
    }
}

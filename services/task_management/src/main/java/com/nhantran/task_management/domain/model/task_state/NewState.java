package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public class NewState extends AbstractTaskState {
    public NewState(Task task) {
        super(task, TaskStatus.NEW);
    }

    @Override
    public void next() {
        changeState(TaskStateFactory.ofTodo(task));
    }
}

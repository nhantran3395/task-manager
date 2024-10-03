package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.TaskStatus;

public interface TaskState {
    TaskStatus getStatusRepresentative();
    void cancel();
    void hold();
    void restore();
    void next();
}
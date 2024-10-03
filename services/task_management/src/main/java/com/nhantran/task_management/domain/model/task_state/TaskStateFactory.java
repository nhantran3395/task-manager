package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public final class TaskStateFactory {
    public static TaskState ofNew(Task task) {
        return new NewState(task);
    }

    public static TaskState ofTodo(Task task) {
        return new TodoState(task);
    }

    public static TaskState ofInProgress(Task task) {
        return new InProgressState(task);
    }

    public static TaskState ofInReview(Task task) {
        return new InReviewState(task);
    }

    public static TaskState ofReadyForTest(Task task) {
        return new ReadyForTestState(task);
    }

    public static TaskState ofInTesting(Task task) {
        return new InTestingState(task);
    }

    public static TaskState ofCompleted(Task task) {
        return new CompletedState(task);
    }

    public static TaskState ofCancelled(Task task) {
        return new CancelledState(task);
    }

    public static TaskState ofPending(Task task) {
        return new PendingState(task);
    }

    public static TaskState fromStatus(TaskStatus status, Task task) {
        switch (status) {
            case NEW -> {
                return new NewState(task);
            }
            case TODO -> {
                return new TodoState(task);
            }
            case IN_PROGRESS -> {
                return new InProgressState(task);
            }
            case IN_REVIEW -> {
                return new InReviewState(task);
            }
            case READY_FOR_TEST -> {
                return new ReadyForTestState(task);
            }
            case IN_TESTING -> {
                return new InTestingState(task);
            }
            case ON_HOLD -> {
                return new PendingState(task);
            }
            case COMPLETED -> {
                return new CompletedState(task);
            }
            case CANCELLED -> {
                return new CancelledState(task);
            }
            default -> throw new IllegalArgumentException("status is not valid");
        }
    }
}

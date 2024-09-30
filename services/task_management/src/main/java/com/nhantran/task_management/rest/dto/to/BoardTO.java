package com.nhantran.task_management.rest.dto.to;

import java.util.List;

public record BoardTO(Long boardId, String name, String iconSlug, List<TaskTO> tasks, String accessRole) {
}

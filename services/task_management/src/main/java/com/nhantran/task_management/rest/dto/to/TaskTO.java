package com.nhantran.task_management.rest.dto.to;

public record TaskTO(Long taskId, String title, String description, String thumbnailUrl, String status) {
}

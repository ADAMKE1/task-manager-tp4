package com.example.taskmanager.dto;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskPriority;
import com.example.taskmanager.entity.TaskStatus;
import java.time.LocalDate;

public record TaskResponse(Long id, String title, String description,
                           TaskPriority priority, TaskStatus status, LocalDate dueDate) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(),
                task.getPriority(), task.getStatus(), task.getDueDate());
    }
}

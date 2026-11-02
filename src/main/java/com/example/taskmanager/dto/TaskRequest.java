package com.example.taskmanager.dto;

import com.example.taskmanager.entity.TaskPriority;
import com.example.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Le titre est obligatoire")
        @Size(max = 120, message = "Le titre ne doit pas depasser 120 caracteres")
        String title,
        @Size(max = 1000) String description,
        TaskPriority priority,
        TaskStatus status,
        @FutureOrPresent(message = "La date limite ne peut pas etre dans le passe") LocalDate dueDate) {
}

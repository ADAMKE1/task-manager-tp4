package com.example.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskPriority;
import com.example.taskmanager.entity.TaskStatus;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    private TaskRequest sampleRequest() {
        return new TaskRequest("Ecrire le TP", "Description", TaskPriority.HIGH,
                TaskStatus.TODO, LocalDate.now().plusDays(7));
    }

    @Test
    void shouldCreateTask() {
        when(repository.save(any(Task.class))).thenAnswer(inv -> {
            Task t = inv.getArgument(0);
            t.setId(1L);
            return t;
        });

        TaskResponse response = service.create(sampleRequest());

        assertEquals(1L, response.id());
        assertEquals("Ecrire le TP", response.title());
        assertEquals(TaskPriority.HIGH, response.priority());
        verify(repository).save(any(Task.class));
    }

    @Test
    void shouldThrowWhenTaskNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void shouldUpdateTask() {
        Task existing = new Task();
        existing.setId(1L);
        existing.setTitle("Ancien titre");
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));

        TaskResponse response = service.update(1L, sampleRequest());

        assertEquals("Ecrire le TP", response.title());
    }

    @Test
    void shouldDeleteExistingTask() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}

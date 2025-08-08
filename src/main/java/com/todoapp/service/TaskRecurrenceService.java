package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.model.RecurrenceType;
import com.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskRecurrenceService {

    private final TaskRepository taskRepository;
    private final TaskOrderService taskOrderService;

    public TaskRecurrenceService(TaskRepository taskRepository, TaskOrderService taskOrderService) {
        this.taskRepository = taskRepository;
        this.taskOrderService = taskOrderService;
    }

    public void handleRecurrentTaskCompletion(Task task) {
        task.setCompletionCount(task.getCompletionCount() + 1);
        if (task.getCompletionCount() >= task.getRecurrenceInterval()) {
            task.getState().setCompleted(true);
            task.getState().setDeleted(true);
            taskRepository.save(task);
            createNextRecurrentTask(task);
        } else {
            task.getState().setCompleted(false);
            int newPosition = taskOrderService.getNextPosition(task.getUserId());
            task.getMetadata().setPosition(newPosition);
            taskRepository.save(task);
        }
    }

    private void createNextRecurrentTask(Task originalTask) {
        Task newTask = Task.builder()
                .userId(originalTask.getUserId())
                .description(originalTask.getDescription())
                .isRecurrent(true)
                .recurrenceType(originalTask.getRecurrenceType())
                .recurrenceInterval(originalTask.getRecurrenceInterval())
                .completionCount(0)
                .build();

        if (originalTask.getMetadata().getCategory() != null) {
            newTask.getMetadata().setCategory(originalTask.getMetadata().getCategory());
        }

        int nextPosition = taskOrderService.getNextPosition(originalTask.getUserId());
        newTask.getMetadata().setPosition(nextPosition);
        taskRepository.save(newTask);
    }
} 
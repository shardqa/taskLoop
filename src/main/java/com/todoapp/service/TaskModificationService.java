package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskModificationService {

    private final TaskRepository taskRepository;
    private final TaskOrderService taskOrderService;

    public TaskModificationService(TaskRepository taskRepository, TaskOrderService taskOrderService) {
        this.taskRepository = taskRepository;
        this.taskOrderService = taskOrderService;
    }

    public Task createTask(String userId, String description, boolean isRecurrent, String category, String recurrenceType, int recurrenceInterval) {
        int nextPosition = taskOrderService.getNextPosition(userId);
        
        Task task = Task.builder()
                .userId(userId)
                .description(description)
                .isRecurrent(isRecurrent)
                .recurrenceType(recurrenceType != null ? com.todoapp.model.RecurrenceType.valueOf(recurrenceType) : com.todoapp.model.RecurrenceType.UNLIMITED)
                .recurrenceInterval(recurrenceInterval)
                .build();
        
        if (category != null && !category.trim().isEmpty()) {
            task.getMetadata().setCategory(category);
        }
        
        task.getMetadata().setPosition(nextPosition);
        
        return taskRepository.save(task);
    }

    public Task updateTask(String taskId, String userId, String description, boolean isRecurrent, String category, String recurrenceType, int recurrenceInterval) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setDescription(description);
        task.setRecurrent(isRecurrent);
        task.setRecurrenceType(recurrenceType != null ? com.todoapp.model.RecurrenceType.valueOf(recurrenceType) : com.todoapp.model.RecurrenceType.UNLIMITED);
        task.setRecurrenceInterval(recurrenceInterval);
        if (category != null) {
            task.getMetadata().setCategory(category);
        }

        return taskRepository.save(task);
    }
} 
package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskRecurrenceService {

    private final TaskRepository taskRepository;
    private final TaskOrderService taskOrderService;

    public TaskRecurrenceService(TaskRepository taskRepository, TaskOrderService taskOrderService) {
        this.taskRepository = taskRepository;
        this.taskOrderService = taskOrderService;
    }

    public void handleRecurrentTaskCompletion(Task task) {
        task.getState().setCompleted(true);
        taskRepository.save(task);
        
        Task newTask = Task.builder()
                .userId(task.getUserId())
                .description(task.getDescription())
                .isRecurrent(true)
                .recurrenceType(task.getRecurrenceType())
                .build();
        
        if (task.getMetadata().getCategory() != null) {
            newTask.getMetadata().setCategory(task.getMetadata().getCategory());
        }
        
        int nextPosition = taskOrderService.getNextPosition(task.getUserId());
        newTask.getMetadata().setPosition(nextPosition);
        
        taskRepository.save(newTask);
    }
} 
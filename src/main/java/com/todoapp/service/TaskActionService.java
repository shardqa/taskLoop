package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskActionService {

    private final TaskRepository taskRepository;
    private final TaskOrderService taskOrderService;
    private final TaskRecurrenceService taskRecurrenceService;

    public TaskActionService(TaskRepository taskRepository, TaskOrderService taskOrderService, TaskRecurrenceService taskRecurrenceService) {
        this.taskRepository = taskRepository;
        this.taskOrderService = taskOrderService;
        this.taskRecurrenceService = taskRecurrenceService;
    }

    public Task completeTask(String taskId, String userId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (task.isRecurrent()) {
            taskRecurrenceService.handleRecurrentTaskCompletion(task);
        } else {
            task.getState().setDeleted(true);
            taskRepository.save(task);
            taskOrderService.reorderTasksAfterDelete(userId, task.getMetadata().getPosition());
        }

        return task;
    }

    public void reorderTasks(String userId, List<String> taskIds) {
        taskOrderService.reorderTasks(userId, taskIds);
    }
} 
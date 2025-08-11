package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLifecycleService {

    private final TaskRepository taskRepository;
    private final TaskOrderService taskOrderService;

    public TaskLifecycleService(TaskRepository taskRepository, TaskOrderService taskOrderService) {
        this.taskRepository = taskRepository;
        this.taskOrderService = taskOrderService;
    }

    public void deleteTask(String taskId, String userId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        task.getState().setDeleted(true);
        taskRepository.save(task);
        taskOrderService.reorderTasksAfterDelete(userId, task.getMetadata().getPosition());
    }

    public List<Task> getDeletedTasks(String userId) {
        return taskRepository.findDeletedByUserId(userId);
    }

    public Task restoreTask(String taskId, String userId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        if (!task.getState().isDeleted()) {
            throw new RuntimeException("Task is not deleted");
        }
        
        task.getState().setDeleted(false);
        task.getMetadata().setPosition(taskOrderService.getNextPosition(userId));
        return taskRepository.save(task);
    }

    public void permanentDeleteTask(String taskId, String userId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        taskRepository.delete(task);
    }
} 
package com.todoapp.exception;

public class TaskNotFoundException extends RuntimeException {
    
    public TaskNotFoundException(String message) {
        super(message);
    }
    
    public TaskNotFoundException(String taskId, String userId) {
        super("Task not found with id: " + taskId + " for user: " + userId);
    }
} 
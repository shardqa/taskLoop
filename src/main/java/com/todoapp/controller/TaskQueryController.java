package com.todoapp.controller;

import com.todoapp.dto.TaskResponse;
import com.todoapp.model.Task;
import com.todoapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskQueryController {

    private final TaskService taskService;

    public TaskQueryController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getUserTasks(Authentication authentication) {
        String userId = authentication.getName();
        List<Task> tasks = taskService.getUserTasks(userId);
        List<TaskResponse> responses = tasks.stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable String taskId, Authentication authentication) {
        String userId = authentication.getName();
        return taskService.getTask(taskId, userId)
                .map(TaskResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<TaskResponse>> getTasksByCategory(@PathVariable String category, Authentication authentication) {
        String userId = authentication.getName();
        List<Task> tasks = taskService.getTasksByCategory(userId, category);
        List<TaskResponse> responses = tasks.stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/recurrent")
    public ResponseEntity<List<TaskResponse>> getRecurrentTasks(Authentication authentication) {
        String userId = authentication.getName();
        List<Task> tasks = taskService.getRecurrentTasks(userId);
        List<TaskResponse> responses = tasks.stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
} 
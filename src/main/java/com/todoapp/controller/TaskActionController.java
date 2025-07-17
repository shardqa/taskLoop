package com.todoapp.controller;

import com.todoapp.dto.TaskResponse;
import com.todoapp.model.Task;
import com.todoapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskActionController {

    private final TaskService taskService;

    public TaskActionController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{taskId}/complete")
    public ResponseEntity<TaskResponse> completeTask(@PathVariable String taskId, Authentication authentication) {
        try {
            String userId = authentication.getName();
            Task task = taskService.completeTask(taskId, userId);
            return ResponseEntity.ok(new TaskResponse(task));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/reorder")
    public ResponseEntity<Void> reorderTasks(@RequestBody List<String> taskIds, Authentication authentication) {
        String userId = authentication.getName();
        taskService.reorderTasks(userId, taskIds);
        return ResponseEntity.ok().build();
    }
} 
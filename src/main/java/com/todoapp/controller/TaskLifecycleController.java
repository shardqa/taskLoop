package com.todoapp.controller;

import com.todoapp.dto.TaskResponse;
import com.todoapp.model.Task;
import com.todoapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
@Tag(name = "Task Lifecycle", description = "Endpoints para gerenciamento do ciclo de vida das tarefas")
@SecurityRequirement(name = "bearerAuth")
public class TaskLifecycleController {

    private final TaskService taskService;

    public TaskLifecycleController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/deleted")
    @Operation(summary = "Listar tarefas deletadas", description = "Lista todas as tarefas deletadas do usuário")
    public ResponseEntity<List<TaskResponse>> getDeletedTasks(Authentication authentication) {
        String userId = authentication.getName();
        List<Task> tasks = taskService.getDeletedTasks(userId);
        List<TaskResponse> responses = tasks.stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{taskId}/restore")
    @Operation(summary = "Restaurar tarefa", description = "Restaura uma tarefa deletada")
    public ResponseEntity<TaskResponse> restoreTask(@PathVariable String taskId, Authentication authentication) {
        try {
            String userId = authentication.getName();
            Task task = taskService.restoreTask(taskId, userId);
            return ResponseEntity.ok(new TaskResponse(task));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{taskId}/permanent")
    @Operation(summary = "Exclusão permanente", description = "Remove permanentemente uma tarefa do banco de dados")
    public ResponseEntity<Void> permanentDeleteTask(@PathVariable String taskId, Authentication authentication) {
        try {
            String userId = authentication.getName();
            taskService.permanentDeleteTask(taskId, userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 
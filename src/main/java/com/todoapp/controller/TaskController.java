package com.todoapp.controller;

import com.todoapp.dto.TaskRequest;
import com.todoapp.dto.TaskResponse;
import com.todoapp.model.Task;
import com.todoapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
@Tag(name = "Tasks", description = "Endpoints para gerenciamento de tarefas")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @Operation(summary = "Criar tarefa", description = "Cria uma nova tarefa para o usuário autenticado")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request, Authentication authentication) {
        String userId = authentication.getName();
        Task task = taskService.createTask(userId, request.getDescription(), request.isRecurrent(), request.getCategory());
        return ResponseEntity.ok(new TaskResponse(task));
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Atualizar tarefa", description = "Atualiza uma tarefa existente do usuário")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable String taskId, 
                                                  @Valid @RequestBody TaskRequest request, 
                                                  Authentication authentication) {
        try {
            String userId = authentication.getName();
            Task task = taskService.updateTask(taskId, userId, request.getDescription(), request.isRecurrent(), request.getCategory());
            return ResponseEntity.ok(new TaskResponse(task));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Excluir tarefa", description = "Remove uma tarefa do usuário (soft delete)")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId, Authentication authentication) {
        try {
            String userId = authentication.getName();
            taskService.deleteTask(taskId, userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 
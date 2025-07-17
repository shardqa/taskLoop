package com.todoapp.controller;

import com.todoapp.dto.TaskPageRequest;
import com.todoapp.dto.TaskPageResponse;
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
@Tag(name = "Task Pagination", description = "Endpoints para listagem paginada de tarefas")
@SecurityRequirement(name = "bearerAuth")
public class TaskPaginationController {

    private final TaskService taskService;

    public TaskPaginationController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/paginated")
    @Operation(summary = "Listar tarefas paginadas", description = "Lista tarefas do usuário com paginação")
    public ResponseEntity<TaskPageResponse> getUserTasksPaginated(
            @Valid TaskPageRequest pageRequest, Authentication authentication) {
        String userId = authentication.getName();
        TaskPageResponse response = taskService.getUserTasksPaginated(userId, pageRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/completed/paginated")
    @Operation(summary = "Listar tarefas concluídas paginadas", description = "Lista tarefas concluídas com paginação")
    public ResponseEntity<TaskPageResponse> getCompletedTasksPaginated(
            @Valid TaskPageRequest pageRequest, Authentication authentication) {
        String userId = authentication.getName();
        TaskPageResponse response = taskService.getCompletedTasksPaginated(userId, pageRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recurrent/paginated")
    @Operation(summary = "Listar tarefas recorrentes paginadas", description = "Lista tarefas recorrentes com paginação")
    public ResponseEntity<TaskPageResponse> getRecurrentTasksPaginated(
            @Valid TaskPageRequest pageRequest, Authentication authentication) {
        String userId = authentication.getName();
        TaskPageResponse response = taskService.getRecurrentTasksPaginated(userId, pageRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}/paginated")
    @Operation(summary = "Listar tarefas por categoria paginadas", description = "Lista tarefas por categoria com paginação")
    public ResponseEntity<TaskPageResponse> getTasksByCategoryPaginated(
            @PathVariable String category, @Valid TaskPageRequest pageRequest, Authentication authentication) {
        String userId = authentication.getName();
        TaskPageResponse response = taskService.getTasksByCategoryPaginated(userId, category, pageRequest);
        return ResponseEntity.ok(response);
    }
} 
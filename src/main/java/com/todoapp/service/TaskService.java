package com.todoapp.service;

import com.todoapp.model.Task;
import org.springframework.stereotype.Service;

import com.todoapp.dto.TaskPageRequest;
import com.todoapp.dto.TaskPageResponse;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskLifecycleService taskLifecycleService;
    private final TaskQueryService taskQueryService;
    private final TaskModificationService taskModificationService;
    private final TaskPaginationService taskPaginationService;
    private final TaskActionService taskActionService;

    public TaskService(TaskLifecycleService taskLifecycleService,
                      TaskQueryService taskQueryService, TaskModificationService taskModificationService,
                      TaskPaginationService taskPaginationService, TaskActionService taskActionService) {
        this.taskLifecycleService = taskLifecycleService;
        this.taskQueryService = taskQueryService;
        this.taskModificationService = taskModificationService;
        this.taskPaginationService = taskPaginationService;
        this.taskActionService = taskActionService;
    }

    public List<Task> getUserTasks(String userId) {
        return taskQueryService.getUserTasks(userId);
    }

    public Task createTask(String userId, String description, boolean isRecurrent, String category) {
        return taskModificationService.createTask(userId, description, isRecurrent, category);
    }

    public Optional<Task> getTask(String taskId, String userId) {
        return taskQueryService.getTask(taskId, userId);
    }

    public Task updateTask(String taskId, String userId, String description, boolean isRecurrent, String category) {
        return taskModificationService.updateTask(taskId, userId, description, isRecurrent, category);
    }

    public void deleteTask(String taskId, String userId) {
        taskLifecycleService.deleteTask(taskId, userId);
    }

    public Task completeTask(String taskId, String userId) {
        return taskActionService.completeTask(taskId, userId);
    }

    public void reorderTasks(String userId, List<String> taskIds) {
        taskActionService.reorderTasks(userId, taskIds);
    }

    public List<Task> getTasksByCategory(String userId, String category) {
        return taskQueryService.getTasksByCategory(userId, category);
    }

    public List<Task> getRecurrentTasks(String userId) {
        return taskQueryService.getRecurrentTasks(userId);
    }

    public List<Task> getDeletedTasks(String userId) {
        return taskLifecycleService.getDeletedTasks(userId);
    }

    public Task restoreTask(String taskId, String userId) {
        return taskLifecycleService.restoreTask(taskId, userId);
    }

    public void permanentDeleteTask(String taskId, String userId) {
        taskLifecycleService.permanentDeleteTask(taskId, userId);
    }

    public TaskPageResponse getUserTasksPaginated(String userId, TaskPageRequest pageRequest) {
        return taskPaginationService.getUserTasksPaginated(userId, pageRequest);
    }

    public TaskPageResponse getCompletedTasksPaginated(String userId, TaskPageRequest pageRequest) {
        return taskPaginationService.getCompletedTasksPaginated(userId, pageRequest);
    }

    public TaskPageResponse getRecurrentTasksPaginated(String userId, TaskPageRequest pageRequest) {
        return taskPaginationService.getRecurrentTasksPaginated(userId, pageRequest);
    }

    public TaskPageResponse getTasksByCategoryPaginated(String userId, String category, TaskPageRequest pageRequest) {
        return taskPaginationService.getTasksByCategoryPaginated(userId, category, pageRequest);
    }
} 
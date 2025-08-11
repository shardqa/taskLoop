package com.todoapp.service;

import com.todoapp.dto.TaskPageRequest;
import com.todoapp.dto.TaskPageResponse;
import com.todoapp.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TaskPaginationService {

    private final TaskQueryService taskQueryService;

    public TaskPaginationService(TaskQueryService taskQueryService) {
        this.taskQueryService = taskQueryService;
    }

    public TaskPageResponse getUserTasksPaginated(String userId, TaskPageRequest pageRequest) {
        Page<Task> page = taskQueryService.getUserTasksPaginated(
            userId, pageRequest.getPage(), pageRequest.getSize(), 
            pageRequest.getSortBy(), pageRequest.getSortDirection()
        );
        return new TaskPageResponse(page);
    }

    public TaskPageResponse getCompletedTasksPaginated(String userId, TaskPageRequest pageRequest) {
        Page<Task> page = taskQueryService.getCompletedTasksPaginated(
            userId, pageRequest.getPage(), pageRequest.getSize(), 
            pageRequest.getSortBy(), pageRequest.getSortDirection()
        );
        return new TaskPageResponse(page);
    }

    public TaskPageResponse getRecurrentTasksPaginated(String userId, TaskPageRequest pageRequest) {
        Page<Task> page = taskQueryService.getRecurrentTasksPaginated(
            userId, pageRequest.getPage(), pageRequest.getSize(), 
            pageRequest.getSortBy(), pageRequest.getSortDirection()
        );
        return new TaskPageResponse(page);
    }

    public TaskPageResponse getTasksByCategoryPaginated(String userId, String category, TaskPageRequest pageRequest) {
        Page<Task> page = taskQueryService.getTasksByCategoryPaginated(
            userId, category, pageRequest.getPage(), pageRequest.getSize(), 
            pageRequest.getSortBy(), pageRequest.getSortDirection()
        );
        return new TaskPageResponse(page);
    }
} 
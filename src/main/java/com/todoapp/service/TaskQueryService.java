package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getUserTasks(String userId) {
        return taskRepository.findByUserIdOrderByMetadataPosition(userId);
    }

    public Optional<Task> getTask(String taskId, String userId) {
        return taskRepository.findByIdAndUserId(taskId, userId);
    }

    public List<Task> getTasksByCategory(String userId, String category) {
        return taskRepository.findByUserIdAndCategoryOrderByMetadataPosition(userId, category);
    }

    public List<Task> getRecurrentTasks(String userId) {
        return taskRepository.findRecurrentTasksByUserId(userId);
    }

    public Page<Task> getUserTasksPaginated(String userId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return taskRepository.findByUserIdOrderByMetadataPosition(userId, pageable);
    }

    public Page<Task> getCompletedTasksPaginated(String userId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return taskRepository.findCompletedByUserId(userId, pageable);
    }

    public Page<Task> getRecurrentTasksPaginated(String userId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return taskRepository.findRecurrentTasksByUserId(userId, pageable);
    }

    public Page<Task> getTasksByCategoryPaginated(String userId, String category, int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return taskRepository.findByUserIdAndCategoryOrderByMetadataPosition(userId, category, pageable);
    }
} 
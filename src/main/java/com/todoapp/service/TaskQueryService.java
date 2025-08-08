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
import java.util.stream.Collectors;

@Service
public class TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getUserTasks(String userId) {
        return taskRepository.findByUserIdAndNotDeleted(userId);
    }

    public List<Task> getUserTasksFiltered(String userId, Boolean completed, Boolean deleted, String category, String recurrence) {
        // Base set: deleted true => only deleted; otherwise not deleted
        List<Task> baseTasks = Boolean.TRUE.equals(deleted)
                ? taskRepository.findDeletedByUserId(userId)
                : taskRepository.findByUserIdAndNotDeleted(userId);

        return baseTasks.stream()
                // completed filter: if provided, match exactly; if null, do not filter by completion
                .filter(task -> completed == null || task.getState().isCompleted() == completed)
                // category filter: if provided and non-empty, match exactly (case-sensitive as stored)
                .filter(task -> category == null || category.isEmpty() || (task.getMetadata() != null && category.equals(task.getMetadata().getCategory())))
                // recurrence filter: "recurrent" or "non-recurrent"
                .filter(task -> {
                    if (recurrence == null || recurrence.isEmpty()) {
                        return true;
                    }
                    if ("recurrent".equalsIgnoreCase(recurrence)) {
                        return task.isRecurrent();
                    }
                    if ("non-recurrent".equalsIgnoreCase(recurrence)) {
                        return !task.isRecurrent();
                    }
                    return true;
                })
                // order by metadata.position if present
                .sorted((a, b) -> {
                    int posA = a.getMetadata() != null ? a.getMetadata().getPosition() : 0;
                    int posB = b.getMetadata() != null ? b.getMetadata().getPosition() : 0;
                    return Integer.compare(posA, posB);
                })
                .collect(Collectors.toList());
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
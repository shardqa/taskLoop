package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskOrderService {

    private final TaskRepository taskRepository;

    public TaskOrderService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public int getNextPosition(String userId) {
        return (int) taskRepository.countActiveTasksByUserId(userId);
    }

    public void reorderTasks(String userId, List<String> taskIds) {
        for (int i = 0; i < taskIds.size(); i++) {
            Optional<Task> taskOpt = taskRepository.findByIdAndUserId(taskIds.get(i), userId);
            if (taskOpt.isPresent()) {
                Task task = taskOpt.get();
                task.getMetadata().setPosition(i);
                taskRepository.save(task);
            }
        }
    }

    public void reorderTasksAfterDelete(String userId, int deletedPosition) {
        List<Task> tasks = taskRepository.findByUserIdOrderByMetadataPosition(userId);
        
        for (Task task : tasks) {
            if (task.getMetadata().getPosition() > deletedPosition) {
                task.getMetadata().setPosition(task.getMetadata().getPosition() - 1);
                taskRepository.save(task);
            }
        }
    }
} 
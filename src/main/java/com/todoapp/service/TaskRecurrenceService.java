package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskRecurrenceService {

    private final TaskRepository taskRepository;
    private final TaskOrderService taskOrderService;

    public TaskRecurrenceService(TaskRepository taskRepository, TaskOrderService taskOrderService) {
        this.taskRepository = taskRepository;
        this.taskOrderService = taskOrderService;
    }

    public void handleRecurrentTaskCompletion(Task task) {
        int originalPosition = task.getMetadata().getPosition();
        task.getState().setCompleted(true);
        task.getState().setDeleted(true);
        taskRepository.save(task);
        taskOrderService.reorderTasksAfterDelete(task.getUserId(), originalPosition);
        createNextRecurrentTask(task);
    }

    private void createNextRecurrentTask(Task originalTask) {
        Task newTask = Task.builder()
                .userId(originalTask.getUserId())
                .description(originalTask.getDescription())
                .isRecurrent(true)
                .recurrenceType(originalTask.getRecurrenceType())
                .recurrenceInterval(originalTask.getRecurrenceInterval())
                .completionCount(0)
                .build();

        if (originalTask.getMetadata().getCategory() != null) {
            newTask.getMetadata().setCategory(originalTask.getMetadata().getCategory());
        }

        int nextPosition = taskOrderService.getNextPosition(originalTask.getUserId());
        newTask.getMetadata().setPosition(nextPosition);
        taskRepository.save(newTask);
    }
}
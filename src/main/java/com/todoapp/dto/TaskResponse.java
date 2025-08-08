package com.todoapp.dto;

import com.todoapp.model.Task;
import com.todoapp.model.RecurrenceType;
import com.todoapp.model.TaskState;
import com.todoapp.model.TaskMetadata;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskResponse {

    private String id;
    private String description;
    private boolean isRecurrent;
    private RecurrenceType recurrenceType;
    private int recurrenceInterval;
    private boolean completed;
    private int position;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String category;
    private TaskState state;
    private TaskMetadata metadata;
    private int completionCount;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.description = task.getDescription();
        this.isRecurrent = task.isRecurrent();
        this.recurrenceType = task.getRecurrenceType();
        this.recurrenceInterval = task.getRecurrenceInterval();
        this.completed = task.getState().isCompleted();
        this.position = task.getMetadata().getPosition();
        this.createdAt = task.getMetadata().getCreatedAt();
        this.completedAt = task.getState().getCompletedAt();
        this.category = task.getMetadata().getCategory();
        this.state = task.getState();
        this.metadata = task.getMetadata();
        this.completionCount = task.getCompletionCount();
    }
} 
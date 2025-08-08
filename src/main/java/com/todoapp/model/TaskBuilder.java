package com.todoapp.model;

public class TaskBuilder {
    private String userId;
    private String description;
    private boolean isRecurrent = false;
    private RecurrenceType recurrenceType = RecurrenceType.UNLIMITED;
    private int recurrenceInterval = 1;
    private int completionCount = 0;
    private int position = 0;
    private String category;

    public TaskBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder isRecurrent(boolean isRecurrent) {
        this.isRecurrent = isRecurrent;
        return this;
    }

    public TaskBuilder recurrenceType(RecurrenceType recurrenceType) {
        this.recurrenceType = recurrenceType;
        return this;
    }

    public TaskBuilder recurrenceInterval(int recurrenceInterval) {
        this.recurrenceInterval = recurrenceInterval;
        return this;
    }

    public TaskBuilder completionCount(int completionCount) {
        this.completionCount = completionCount;
        return this;
    }

    public TaskBuilder position(int position) {
        this.position = position;
        return this;
    }

    public TaskBuilder category(String category) {
        this.category = category;
        return this;
    }

    public Task build() {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription(description);
        task.setRecurrent(isRecurrent);
        task.setRecurrenceType(recurrenceType);
        task.setRecurrenceInterval(recurrenceInterval);
        task.setCompletionCount(completionCount);
        if (task.getMetadata() != null) {
            task.getMetadata().setPosition(position);
            task.getMetadata().setCategory(category);
        }
        return task;
    }
} 
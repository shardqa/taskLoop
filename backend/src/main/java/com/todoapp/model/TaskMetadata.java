package com.todoapp.model;

import java.time.LocalDateTime;

public class TaskMetadata {
    private LocalDateTime createdAt;
    private int position;
    private String category;

    public TaskMetadata() {
        this.createdAt = LocalDateTime.now();
        this.position = 0;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
} 
package com.todoapp.dto;

import jakarta.validation.constraints.Min;

public class TaskPageRequest {

    @Min(value = 0, message = "Page must be 0 or greater")
    private int page = 0;

    @Min(value = 1, message = "Size must be 1 or greater")
    private int size = 20;

    private String sortBy = "metadata.position";
    private String sortDirection = "asc";

    public TaskPageRequest() {}

    public TaskPageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public TaskPageRequest(int page, int size, String sortBy, String sortDirection) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
} 
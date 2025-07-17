package com.todoapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    @Indexed
    private String userId;
    private String description;
    private boolean isRecurrent = false;
    private RecurrenceType recurrenceType = RecurrenceType.UNLIMITED;
    private TaskState state = new TaskState();
    private TaskMetadata metadata = new TaskMetadata();

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }
} 
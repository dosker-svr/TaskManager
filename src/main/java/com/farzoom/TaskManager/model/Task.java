package com.farzoom.TaskManager.model;

import java.time.LocalDateTime;

public class Task {
    // TODO: сделать поле id - неизменяемым
    private Long taskId;
    private String name;
    private String description;
    private LocalDateTime dateLastModification;

    public Task() {
    }

    public Task(Long taskId, String name, String description) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateLastModification() {
        return dateLastModification;
    }

    public void setDateLastModification(LocalDateTime dateLastModification) {
        this.dateLastModification = dateLastModification;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateLastModification=" + dateLastModification +
                '}';
    }
}

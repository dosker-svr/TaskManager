package com.farzoom.TaskManager.exception;

public class TaskExistsException extends RuntimeException {
    public TaskExistsException(String message) {
        super(message);
    }
}

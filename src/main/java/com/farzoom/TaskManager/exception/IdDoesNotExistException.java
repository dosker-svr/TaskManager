package com.farzoom.TaskManager.exception;

public class IdDoesNotExistException extends RuntimeException {
    public IdDoesNotExistException(String message) {
        super(message);
    }
}

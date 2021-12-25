package com.farzoom.TaskManager.controller;

import com.farzoom.TaskManager.exception.IdDoesNotExistException;
import com.farzoom.TaskManager.exception.IdUpdateException;
import com.farzoom.TaskManager.exception.TaskExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlerNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskExistsException.class)
    public ResponseEntity<String> handlerTaskExistsException(TaskExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdDoesNotExistException.class)
    public ResponseEntity<String> handlerTaskExistsException(IdDoesNotExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdUpdateException.class)
    public ResponseEntity<String> handlerTaskExistsException(IdUpdateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

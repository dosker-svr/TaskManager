package com.farzoom.TaskManager.service;

import com.farzoom.TaskManager.exception.IdDoesNotExistException;
import com.farzoom.TaskManager.exception.IdUpdateException;
import com.farzoom.TaskManager.exception.TaskExistsException;
import com.farzoom.TaskManager.model.Task;
import com.farzoom.TaskManager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(Task task) {
        if (repository.containsTaskId(task.getTaskId())) {
            throw new TaskExistsException("Task already exists");
        }
        if (task.getDateLastModification() == null) {
            task.setDateLastModification(LocalDateTime.now());
        }
        return repository.addTaskToStorage(task);
    }

    public Task getTaskById(Long taskId) {
        if (taskId == null) {
            throw new NullPointerException("Task id is null");
        }
        if (!repository.containsTaskId(taskId)) {
            throw new IdDoesNotExistException("Repository doesn't contain id");
        }

        return repository.getTask(taskId);
    }

    public Task updateTask(Long taskId, Task task) {
        Long updatedTaskId = task.getTaskId();
        String taskName = task.getName();
        String description = task.getDescription();

        if (taskId == null) {
            throw new NullPointerException("Task id is null");
        }
        if (!repository.containsTaskId(taskId)) {
            throw new IdDoesNotExistException("Repository doesn't contain id");
        }
        if (updatedTaskId != null) {
            throw new IdUpdateException("You can't update the task Id");
        }
        if (taskName != null) {
            repository.getTask(taskId).setName(taskName);
        }
        if (description != null) {
            repository.getTask(taskId).setDescription(description);
        }
        repository.getTask(taskId).setDateLastModification(LocalDateTime.now());

        return repository.getTask(taskId);
    }

    public Task deleteTask(Long taskId) {
        if (taskId == null) {
            throw new NullPointerException("Task id is null");
        }
        if (!repository.containsTaskId(taskId)) {
            throw new IdDoesNotExistException("Repository doesn't contain id");
        }

        return repository.deleteTask(taskId);
    }

    public List<Task> getTasksList() {
        List<Task> tasksListSortedByDate = repository.getTasksListSortedByDate();
        if (tasksListSortedByDate.isEmpty()) {
            throw new NullPointerException("Tasks list is empty");
        }
        return tasksListSortedByDate;
    }
}

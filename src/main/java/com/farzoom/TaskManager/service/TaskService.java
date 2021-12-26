package com.farzoom.TaskManager.service;

import com.farzoom.TaskManager.exception.IdDoesNotExistException;
import com.farzoom.TaskManager.exception.IdUpdateException;
import com.farzoom.TaskManager.exception.TaskExistsException;
import com.farzoom.TaskManager.model.Task;
import com.farzoom.TaskManager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(Task task) {
        if (repository.existsById(task.getTaskId())) {
            throw new TaskExistsException("Task already exists");
        }
        if (task.getDateLastModification() == null) {
            task.setDateLastModification(LocalDateTime.now());
        }
        return repository.save(task);
    }

    public Task getTaskById(Long taskId) {
        if (taskId == null) {
            throw new NullPointerException("Task id is null");
        }
        if (!repository.existsById(taskId)) {
            throw new IdDoesNotExistException("Repository doesn't contain id");
        }

        return repository.findById(taskId).get();
    }

    public Task updateTask(Long taskId, Task taskFromBody) {
        Long updatedTaskId = taskFromBody.getTaskId();
        String taskName = taskFromBody.getName();
        String description = taskFromBody.getDescription();

        if (taskId == null) {
            throw new NullPointerException("Task id is null");
        }
        if (!repository.existsById(taskId)) {
            throw new IdDoesNotExistException("Repository doesn't contain id");
        }

        Task taskFromRepository = repository.findById(taskId).get();

        if (updatedTaskId != null) {
            throw new IdUpdateException("You can't update the taskFromBody Id");
        }
        if (taskName != null) {
            taskFromRepository.setName(taskName);
        }
        if (description != null) {
            taskFromRepository.setDescription(description);
        }
        taskFromRepository.setDateLastModification(LocalDateTime.now());

        return repository.save(taskFromRepository);
    }

    public Task deleteTask(Long taskId) {
        if (taskId == null) {
            throw new NullPointerException("Task id is null");
        }
        if (!repository.existsById(taskId)) {
            throw new IdDoesNotExistException("Repository doesn't contain id");
        }

        Task taskFromRepository = repository.findById(taskId).get();

        repository.deleteById(taskId);

        return taskFromRepository;
    }

    public List<Task> getTasksList() {
        List<Task> tasksListSortedByDate = new ArrayList<>();
        repository.findAll().forEach(task -> tasksListSortedByDate.add(task));
        if (tasksListSortedByDate.isEmpty()) {
            throw new NullPointerException("Tasks list is empty");
        }
        return tasksListSortedByDate.stream().
                sorted(Comparator.comparing(Task::getDateLastModification).reversed()).
                collect(Collectors.toUnmodifiableList());
    }
}

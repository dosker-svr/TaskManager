package com.farzoom.TaskManager;

import com.farzoom.TaskManager.model.Task;
import com.farzoom.TaskManager.repository.TaskRepository;
import com.farzoom.TaskManager.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TaskServiceTest {
    private static TaskRepository repository;

    private static TaskService service;
    private static Long taskId;
    private static Task task;
    private static String taskNameFromBody;
    private static String taskDescriptionFromBody;
    private static Task taskFromBody;
    private static Task updatedTask;

    @BeforeAll
    static void initVar() {
        repository = Mockito.mock(TaskRepository.class);
        service = new TaskService(repository);
        taskId = 999L;
        task = new Task(taskId, "task", "description: bla bla");
        taskNameFromBody = "fromBody";
        taskDescriptionFromBody = "description from body";
        taskFromBody = new Task(null, taskNameFromBody, taskDescriptionFromBody);
        updatedTask = new Task(taskId, taskNameFromBody, taskDescriptionFromBody);

    }

    @Test
    void createTaskTest() {
        Mockito.when(repository.existsById(taskId)).thenReturn(false);
        Mockito.when(repository.save(task)).thenReturn(task);

        Assertions.assertEquals(task, service.createTask(task));
    }

    @Test
    void getTaskByIdTest() {
        Mockito.when(repository.existsById(taskId)).thenReturn(true);
        Mockito.when(repository.findById(taskId)).thenReturn(Optional.of(task));

        Assertions.assertEquals(task, service.getTaskById(taskId));
    }

    @Test
    void updateTaskTest() {
        Mockito.when(repository.existsById(taskId)).thenReturn(true);
        Mockito.when(repository.findById(taskId)).thenReturn(Optional.of(task));
        Mockito.when(repository.save(task)).thenReturn(task);

        task.setName(taskNameFromBody);
        task.setDescription(taskDescriptionFromBody);

        Task resultTaskAfterUpdate = service.updateTask(taskId, taskFromBody);
        updatedTask.setDateLastModification(task.getDateLastModification());

        Assertions.assertEquals(updatedTask.getTaskId(), resultTaskAfterUpdate.getTaskId());
    }

    @Test
    void deleteTaskTest() {
        Mockito.when(repository.existsById(taskId)).thenReturn(true);
        Mockito.when(repository.findById(taskId)).thenReturn(Optional.of(task));

        Assertions.assertEquals(task, service.deleteTask(taskId));
    }

    @Test
    void getTasksListTest() {
        List<Task> list = Collections.singletonList(task);
        Mockito.when(repository.findAll()).thenReturn(list);

        Assertions.assertEquals(list, service.getTasksList());
    }
}

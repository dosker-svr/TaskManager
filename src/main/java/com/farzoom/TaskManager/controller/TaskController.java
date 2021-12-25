package com.farzoom.TaskManager.controller;

import com.farzoom.TaskManager.model.Task;
import com.farzoom.TaskManager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/{taskId}")
    public String getTask(@PathVariable Long taskId) {
        return service.getTaskById(taskId).getDescription();
    }

    @GetMapping("/get_list_tasks")
    public List<Task> getTasksList() {
        return service.getTasksList();
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/update/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return service.updateTask(taskId, task);
    }

    @DeleteMapping("/delete/{taskId}")
    public Task deleteTask(@PathVariable Long taskId) {
        return service.deleteTask(taskId);
    }
}

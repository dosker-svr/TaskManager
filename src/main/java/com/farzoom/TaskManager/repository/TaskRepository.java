package com.farzoom.TaskManager.repository;

import com.farzoom.TaskManager.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}

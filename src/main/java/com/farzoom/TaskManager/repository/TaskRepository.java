package com.farzoom.TaskManager.repository;

import com.farzoom.TaskManager.model.Task;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TaskRepository {
    // TODO: продумать атомарность изменений с sql (исключить промежуточное состояние в параллельном чтении/записи)
    // TODO: реализация с postgres
    private final Map<Long, Task> tasksStorage = new ConcurrentHashMap<>();

    public Task addTaskToStorage(Task task) {
        tasksStorage.put(task.getTaskId(), task);
        return task;
    }

    public boolean containsTaskId(Long taskId) {
        return tasksStorage.containsKey(taskId);
    }

    public Task getTask(Long id) {
        return tasksStorage.get(id);
    }

    public Task deleteTask(Long id) {
        return tasksStorage.remove(id);
    }

    public List<Task> getTasksListSortedByDate() {
        return tasksStorage.values().
                stream().
                sorted(Comparator.comparing(Task::getDateLastModification).reversed()).
                collect(Collectors.toUnmodifiableList());
    }
}

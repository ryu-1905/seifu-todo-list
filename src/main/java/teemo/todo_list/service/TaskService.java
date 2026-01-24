package teemo.todo_list.service;

import java.util.List;

import teemo.todo_list.model.Task;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskById(Long id);

    void addTask(Task task);

    void updateTask(Task task);

    void toggleTaskCompletion(Long id);

    void deleteTask(Long id);
}

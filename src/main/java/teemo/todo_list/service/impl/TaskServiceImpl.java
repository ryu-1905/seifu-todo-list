package teemo.todo_list.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import teemo.todo_list.model.Task;
import teemo.todo_list.repository.TaskRepository;
import teemo.todo_list.service.TaskService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskServiceImpl implements TaskService {

    final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll(
                Sort.by(Sort.Order.asc("completed"), Sort.Order.desc("dateTime")));
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void toggleTaskCompletion(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(!task.getCompleted());
            taskRepository.save(task);
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

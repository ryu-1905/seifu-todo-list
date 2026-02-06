package teemo.todo_list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import teemo.todo_list.model.Task;
import teemo.todo_list.service.AccountService;
import teemo.todo_list.service.TaskService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskController {

    final TaskService taskService;
    final AccountService accountService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("account", accountService.getCurrentAccount());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task-list";
    }

    @GetMapping("/add-task")
    public String getAddTaskForm() {
        return "add-task";
    }

    @PostMapping("/add-task")
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/";
    }

    @GetMapping("/edit-task/{id}")
    public String getEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit-task";
    }

    @PostMapping("/edit-task")
    public String editTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/";
    }

    @PostMapping("/task-complete/{id}")
    public String changeComplete(@PathVariable Long id) {
        taskService.toggleTaskCompletion(id);
        return "redirect:/";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}

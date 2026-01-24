package teemo.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import teemo.todo_list.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

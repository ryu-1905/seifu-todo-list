package teemo.todo_list.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import teemo.todo_list.model.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}

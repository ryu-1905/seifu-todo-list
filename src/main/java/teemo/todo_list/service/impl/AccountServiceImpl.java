package teemo.todo_list.service.impl;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import teemo.todo_list.model.Account;
import teemo.todo_list.repository.UserRepository;
import teemo.todo_list.service.AccountService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {

    final UserRepository userRepository;

    @Override
    public void register(String username, String email, String password) throws IllegalStateException {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new IllegalStateException("Email already in use");
                });

        userRepository.save(new Account(username, email, password));
    }

}

package teemo.todo_list.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import teemo.todo_list.model.Account;
import teemo.todo_list.repository.AccountRepository;
import teemo.todo_list.service.AccountService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {

    final AccountRepository accountRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public void register(String username, String email, String password) throws IllegalStateException {
        accountRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new IllegalStateException("Email already in use");
                });

        accountRepository.save(new Account(username, email, passwordEncoder.encode(password)));
    }

}

package teemo.todo_list.service.impl;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Override
    public Account getCurrentAccount() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return accountRepository.findById(Long.parseLong(userDetails.getUsername()))
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    @Override
    public void updateAccount(Account account) {
        Account existingAccount = accountRepository.findById(account.getId())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (account.getUsername() != null && !account.getUsername().isEmpty()) {
            existingAccount.setUsername(account.getUsername());
        }
        if (account.getEmail() != null && !account.getEmail().isEmpty()) {
            existingAccount.setEmail(account.getEmail());
        }
        if (account.getPassword() != null && !account.getPassword().isEmpty()) {
            existingAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        }

        accountRepository.save(existingAccount);
    }

}

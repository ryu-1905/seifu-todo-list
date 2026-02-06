package teemo.todo_list.service;

import teemo.todo_list.model.Account;

public interface AccountService {
    void register(String username, String email, String password);

    Account getCurrentAccount();

    void updateAccount(Account account);
}

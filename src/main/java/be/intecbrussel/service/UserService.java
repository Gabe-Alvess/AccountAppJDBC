package be.intecbrussel.service;

import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;
import be.intecbrussel.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final AccountService accountService = new AccountService();
    private final UserRepository userRepository = new UserRepository();
    public boolean createUser(User user) {
       boolean success = accountService.createAccount(user.getAccount());

       if (success) {
           return userRepository.createUser(user);
       }
       return false;
    }

    public Optional<User> getUser(Account account) {
        return userRepository.findUser(account);
    }
}

package be.intecbrussel.service;

import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;

import java.util.Optional;

public class LoggingService {
    private final UserService userService = new UserService();
    private final AccountService accountService = new AccountService();

    public boolean register(String fname, String lname, String email, String passw) {

        Account account = new Account(email, passw);

        User user = new User(fname, lname, account);

        return userService.createUser(user);
    }

    public Optional<User> loginUser(String email, String passw) {
        Optional<Account> account = accountService.lookForAccount(email);

        if (account.isEmpty() || !account.get().getPassw().equals(passw)) {
            return Optional.empty();
        }

        return userService.getUser(account.get());
    }

    public boolean changeUserPassword(String userEmail, String newPass) {
        return accountService.changePassword(userEmail, newPass);
    }

    public boolean changeUserEmail(String userEmail, String newEmail) {
        return accountService.changeEmail(userEmail, newEmail);
    }

    public boolean deleteUserAccount(String userEmail) {
        return accountService.deleteAccount(userEmail);
    }

    public boolean deleteUserInfo(String userEmail) {
        return accountService.deleteUser(userEmail);
    }
}

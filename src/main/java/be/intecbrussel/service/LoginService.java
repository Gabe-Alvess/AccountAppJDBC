package be.intecbrussel.service;

import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;

import java.util.List;
import java.util.Optional;

public class LoginService implements ILoginService {
    private final UserService userService = new UserService();
    private final AccountService accountService = new AccountService();

    @Override
    public boolean register(String fname, String lname, String email, String passw) {

        Account account = new Account(email, passw);

        User user = new User(fname, lname, account);

        return userService.createUser(user);
    }

    @Override
    public Optional<User> loginUser(String email, String passw) {
        Optional<Account> account = accountService.lookForAccount(email);

        if (account.isPresent() && account.get().getPassw().equals(passw)) {
            return userService.getUser(account.get());
        }

        return Optional.empty();
    }

    @Override
    public boolean changeUserPassword(String userEmail, String newPass) {
        return accountService.changePassword(userEmail, newPass);
    }

    @Override
    public boolean changeUserEmail(String userEmail, String newEmail) {
        return accountService.changeEmail(userEmail, newEmail);
    }

    @Override
    public boolean deleteUserAccount(String userEmail) {
        return accountService.deleteAccount(userEmail);
    }

    @Override
    public boolean deleteUserInfo(String userEmail) {
        return accountService.deleteUser(userEmail);
    }

    @Override
    public boolean changeName(String newFName, String newLName, String email) {
        return userService.changeNameInfo(newFName, newLName, email);
    }

    @Override
    public boolean registerManyUsers(List<User> userList) {
        return userService.createManyUsers(userList);
    }
}

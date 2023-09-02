package be.intecbrussel.service;

import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;
import be.intecbrussel.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private final AccountService accountService = new AccountService();
    private final UserRepository userRepository = new UserRepository();

    @Override
    public boolean createUser(User user) {
        boolean success = accountService.createAccount(user.getAccount());

        if (success) {
            return userRepository.createUser(user);
        }

        return false;
    }

    @Override
    public Optional<User> getUser(Account account) {
        return userRepository.findUser(account);
    }

    @Override
    public boolean changeNameInfo(String newFName, String newLName, String email) {
        return userRepository.changeName(newFName, newLName, email);
    }

    @Override
    public boolean createManyUsers(List<User> userList) {
        List<Account> accountList = new ArrayList<>();

        for (User user : userList) {
            accountList.add(user.getAccount());
        }

        boolean succes = accountService.createManyAccounts(accountList);

        if (succes) {
            return userRepository.createManyUsers(userList);
        }
        return false;
    }
}

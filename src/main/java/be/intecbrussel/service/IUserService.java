package be.intecbrussel.service;

import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    boolean createUser(User user);

    Optional<User> getUser(Account account);

    boolean changeNameInfo(String newFName, String newLName, String email);

    boolean createManyUsers(List<User> userList);
}

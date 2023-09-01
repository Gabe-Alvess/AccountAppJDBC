package be.intecbrussel.repository;

import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepo {
    boolean createUser(User user);

    Optional<User> findUser(Account account);

    boolean changeName(String firstName, String lastName, String email);

    boolean createManyUsers(List<User> userList);
}

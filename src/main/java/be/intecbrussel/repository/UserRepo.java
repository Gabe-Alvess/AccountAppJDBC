package be.intecbrussel.repository;

import be.intecbrussel.modal.Account;
import be.intecbrussel.modal.User;

import java.util.Optional;

public interface UserRepo {
    boolean createUser(User user);
    Optional<User> findUser(Account account);
}

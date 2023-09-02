package be.intecbrussel.service;

import be.intecbrussel.modal.User;

import java.util.List;
import java.util.Optional;

public interface ILoginService {
    boolean register(String fname, String lname, String email, String passw);

    Optional<User> loginUser(String email, String passw);

    boolean changeUserPassword(String userEmail, String newPass);

    boolean changeUserEmail(String userEmail, String newEmail);

    boolean deleteUserAccount(String userEmail);

    boolean deleteUserInfo(String userEmail);

    boolean changeName(String newFName, String newLName, String email);

    boolean registerManyUsers(List<User> userList);
}

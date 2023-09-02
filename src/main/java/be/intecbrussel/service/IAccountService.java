package be.intecbrussel.service;

import be.intecbrussel.modal.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    boolean createAccount(Account account);

    Optional<Account> lookForAccount(String email);

    boolean changePassword(String email, String newPass);

    boolean changeEmail(String email, String newEmail);

    boolean deleteAccount(String userEmail);

    boolean deleteUser(String userEmail);

    boolean createManyAccounts(List<Account> accountList);
}

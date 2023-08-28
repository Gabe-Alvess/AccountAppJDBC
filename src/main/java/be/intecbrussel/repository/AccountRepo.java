package be.intecbrussel.repository;

import be.intecbrussel.modal.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepo {
    boolean createAccount(Account account);

    Optional<Account> getAccount(String email);

    boolean updateEmail(String email, String newEmail);

    boolean updatePassword(String email, String newPass);

    boolean wipeAccount(String email);

    boolean wipeUser(String email);

    boolean createManyAccounts(List<Account> accountList);
}

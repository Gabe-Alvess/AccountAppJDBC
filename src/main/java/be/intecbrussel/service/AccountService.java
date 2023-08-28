package be.intecbrussel.service;

import be.intecbrussel.modal.Account;
import be.intecbrussel.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private final AccountRepository accountRepository = new AccountRepository();

    public boolean createAccount(Account account) {
        return accountRepository.createAccount(account);
    }
    public Optional<Account> lookForAccount(String email) {
        return accountRepository.getAccount(email);
    }
    public boolean changePassword(String email, String newPass) {
        return accountRepository.updatePassword(email, newPass);
    }

    public boolean changeEmail(String email, String newEmail) {
        return accountRepository.updateEmail(email, newEmail);
    }

    public boolean deleteAccount(String userEmail) {
        return accountRepository.wipeAccount(userEmail);
    }

    public boolean deleteUser(String userEmail) {
        return accountRepository.wipeUser(userEmail);
    }

    public boolean createManyAccounts(List<Account> accountList) {
        return accountRepository.createManyAccounts(accountList);
    }
}

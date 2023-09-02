package be.intecbrussel.service;

import be.intecbrussel.modal.Account;
import be.intecbrussel.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountService implements IAccountService {
    private final AccountRepository accountRepository = new AccountRepository();

    @Override
    public boolean createAccount(Account account) {
        return accountRepository.createAccount(account);
    }

    @Override
    public Optional<Account> lookForAccount(String email) {
        return accountRepository.getAccount(email);
    }

    @Override
    public boolean changePassword(String email, String newPass) {
        return accountRepository.updatePassword(email, newPass);
    }

    @Override
    public boolean changeEmail(String email, String newEmail) {
        return accountRepository.updateEmail(email, newEmail);
    }

    @Override
    public boolean deleteAccount(String userEmail) {
        return accountRepository.wipeAccount(userEmail);
    }

    @Override
    public boolean deleteUser(String userEmail) {
        return accountRepository.wipeUser(userEmail);
    }

    @Override
    public boolean createManyAccounts(List<Account> accountList) {
        return accountRepository.createManyAccounts(accountList);
    }
}

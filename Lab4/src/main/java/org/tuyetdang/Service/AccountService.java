package org.tuyetdang.Service;

import org.tuyetdang.Entity.Account;
import org.tuyetdang.Repository.AccountRepository;

import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void add(Account account) {
        accountRepository.create(account);
    }

    public void update(Account account) {
        accountRepository.update(account);
    }

    public Account getById(long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public void delete(long id) {
        accountRepository.delete(id);
    }

}

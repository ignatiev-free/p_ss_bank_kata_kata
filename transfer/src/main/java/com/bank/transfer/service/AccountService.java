package com.bank.transfer.service;

import com.bank.transfer.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAll();
    Optional<Account> getById(Long id);
    Optional<Account> getByAccountNumber(Long accountNumber);
    void save(Account account);
    void update(Long id, Account account);
    void delete(Long id);


}

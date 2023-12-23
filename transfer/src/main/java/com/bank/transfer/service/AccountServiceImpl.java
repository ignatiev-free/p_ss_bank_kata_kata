package com.bank.transfer.service;

import com.bank.transfer.entity.Account;
import com.bank.transfer.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import com.bank.transfer.aop.DeleteToLog;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

/**
 * сервисный класс для работы с объектами {@link Account}, использует AccountRepository для выполнения операций с базой данных
 */

@Slf4j
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAll() {
        log.info("finding all accounts");
        var getAllAccountEntities = accountRepository.findAll();
        log.info("getAll accountEntities success, count = {}", getAllAccountEntities.size());
        return getAllAccountEntities;
    }

    @Override
    public Optional<Account> getById(Long id) {
        log.info("finding account by id = {}", id);
        var getByIdAccountEntity = accountRepository.findById(id);
        log.info("getById accountEntity success, getByIdAccountEntity.isPresent() = {}",
                getByIdAccountEntity.isPresent());
        return getByIdAccountEntity;
    }

    @Override
    public Optional<Account> getByAccountNumber(Long accountNumber) {
        log.info("finding account by accountNumber = {}", accountNumber);
        var getByNumberAccountEntity = accountRepository.getByAccountNumber(accountNumber);
        log.info("getByAccountNumber accountEntity success, getByNumberAccountTransferEntity.isPresent() = {}",
                getByNumberAccountEntity.isPresent());
        return getByNumberAccountEntity;
    }

    @Override
    @Transactional
    public void save(Account account) {
        log.info("try to save accountTransferEntity = {}", account);
        accountRepository.save(account);
        log.info("save accountEntity success, id = {}", account.getId());
    }

    @Override
    @Transactional
    public void update(Long id, Account account) {
        account.setId(id);
        log.info("try to update accountTransferEntity = {}", account);
        accountRepository.save(account);
        log.info("update accountTransferEntity success, id = {}", account.getId());
    }

    @Override
    @Transactional
    @Timed("deleteFromService")
    @DeleteToLog
    public void delete(Long id) {
        log.info("try to delete accountTransferEntity with id = {}", id);
        accountRepository.deleteById(id);
        log.info("success delete accountTransferEntity with id = {}", id);
    }
}

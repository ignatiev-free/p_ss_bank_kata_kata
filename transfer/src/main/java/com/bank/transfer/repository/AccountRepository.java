package com.bank.transfer.repository;

import com.bank.transfer.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * интерфейс для взаимодействия с базой данных и выполнения операций связанных с сущностью Account.
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> getByAccountNumber(Long accountNumber);

}

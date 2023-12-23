package com.bank.transfer.validator;

import com.bank.transfer.entity.Account;
import com.bank.transfer.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class AccountNumberUniqueValidator implements Validator {

    private final AccountService accountService;

    public AccountNumberUniqueValidator(AccountService accountTransferService) {
        this.accountService = accountTransferService;
    }

    @Override
    public boolean supports(Class<?> classify) {
        return Account.class.equals(classify);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account accountTransferEntity = (Account) target;
        log.info("try to validate accountTransferEntity: {}", accountTransferEntity);
        accountService.getByAccountNumber(accountTransferEntity.getAccountNumber())
                .ifPresent((value) -> validateAccountNumber(accountTransferEntity, value, errors)
                );
        log.info("success validate accountTransfer: {}", accountTransferEntity);
    }

    private void validateAccountNumber(Account validated, Account fromDataBase, Errors errors) {
        if (!compareTransfer(validated, fromDataBase)) {
            errors.rejectValue(
                    "accountNumber",
                    String.format("accountNumber %s already exist!", fromDataBase.getAccountNumber()),
                    String.format("accountNumber %s already exist!", fromDataBase.getAccountNumber()));
        }
    }

    private boolean compareTransfer(Account validated, Account fromDataBase) {
        if (validated.getId() == null) {
            return false;
        }
        return validated.getId().equals(fromDataBase.getId());
    }

}
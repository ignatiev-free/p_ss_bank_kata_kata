package com.bank.transfer.exception;
public class AccountValidatorException extends RuntimeException {

    public AccountValidatorException(String message) {
        super(message);
    }
}
package com.bank.transfer.exception;
public class AccountEntityNotFoundException extends RuntimeException {

    public AccountEntityNotFoundException(String message) {
        super(message);
    }
}
package com.bank.transfer.exception;
public class CardValidatorException extends RuntimeException {

    public CardValidatorException(String message) {
        super(message);
    }
}
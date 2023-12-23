package com.bank.transfer.exception;
public class CardEntityNotFoundException extends RuntimeException {

    public CardEntityNotFoundException(String message) {
        super(message);
    }
}
package com.bank.transfer.handler;

import com.bank.transfer.exception.CardEntityNotFoundException;
import com.bank.transfer.exception.CardValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CardExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardEntityNotFoundException.class)
    public ResponseEntity<String> handleCardTransferNotFoundException(CardEntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardValidatorException.class)
    public ResponseEntity<String> handleCardTransferException(CardValidatorException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
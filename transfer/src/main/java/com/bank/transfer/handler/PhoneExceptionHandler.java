package com.bank.transfer.handler;

import com.bank.transfer.exception.PhoneEntityNotFoundException;
import com.bank.transfer.exception.PhoneValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class PhoneExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PhoneEntityNotFoundException.class)
    public ResponseEntity<String> handlePhoneTransferNotFoundException(PhoneEntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneValidatorException.class)
    public ResponseEntity<String> handleCardTransferException(PhoneValidatorException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
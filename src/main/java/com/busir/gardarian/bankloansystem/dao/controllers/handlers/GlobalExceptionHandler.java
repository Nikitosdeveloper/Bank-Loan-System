package com.busir.gardarian.bankloansystem.dao.controllers.handlers;

import com.busir.gardarian.bankloansystem.service.exception.EmailAlreadyExistException;
import com.busir.gardarian.bankloansystem.service.exception.IncorrectPasswordException;
import com.busir.gardarian.bankloansystem.service.exception.UserIsNotActive;
import com.busir.gardarian.bankloansystem.service.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(final Exception ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler({
            IncorrectPasswordException.class
    })
    public ResponseEntity<String> handleIncorrectPasswordException(final Exception ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(final Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = UserIsNotActive.class)
    public ResponseEntity<String> handleUserIsNotActive(final Exception ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }

}

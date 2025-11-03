package com.busir.gardarian.bankloansystem.dao.controllers.handlers;

import com.busir.gardarian.bankloansystem.service.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
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

    @ExceptionHandler(value = LoanPurposeNotFound.class)
    public ResponseEntity<String> handleLoanPurposeNotFound(final Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = ClientAdditionalInfoNotFoundException.class)
    public ResponseEntity<String> handleClientAdditionalInfoNotFoundException(final Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    public ResponseEntity<String> handleMalformedJwtException(final Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = SignatureException.class)
    public ResponseEntity<String> handleSignatureException(final Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<String> handleException(final Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token is expired");
    }

    @ExceptionHandler(value = LoanApplicationNotFoundException.class)
    public ResponseEntity<String> handleLoanApplicationNotFoundException(final Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(value = DocumentsNameException.class)
    public ResponseEntity<String> handleDocumentsNameException(final Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = DocumentIsEmptyException.class)
    public ResponseEntity<String> handleDocumentIsEmptyException(final Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = IncorrectLoanApplicationIdException.class)
    public ResponseEntity<String> handleIncorrectLoanApplicationIdException(final Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = DocumentNotFoundException.class)
    public ResponseEntity<String> handleDocumentNotFoundException(final Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}

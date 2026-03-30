package org.example.authapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(
            RegistrationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(
            BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}

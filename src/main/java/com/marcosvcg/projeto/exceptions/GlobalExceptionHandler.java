package com.marcosvcg.projeto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exceptions.UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exceptions.UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exceptions.SamePasswordException.class)
    public ResponseEntity<Object> handleSamePasswordException(Exceptions.SamePasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(Exceptions.SameUsernameException.class)
    public ResponseEntity<Object> handleSamePasswordException(Exceptions.SameUsernameException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(Exceptions.EmailAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleEmailAlreadyRegisteredException(Exceptions.EmailAlreadyRegisteredException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(Exceptions.UsernameAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleUsernameAlreadyRegisteredException(Exceptions.UsernameAlreadyRegisteredException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}

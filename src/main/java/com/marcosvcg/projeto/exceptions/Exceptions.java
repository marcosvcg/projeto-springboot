package com.marcosvcg.projeto.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;

public class Exceptions {

    public static class UserNotFoundException extends EntityNotFoundException {
        public UserNotFoundException(Long userId) {
            super("User with ID " + userId + " does not exists.");
        }
    }

    public static class SamePasswordException extends IllegalArgumentException {
        public SamePasswordException() {
            super("Please choose a new password different from your previous one.");
        }
    }

    public static class SameUsernameException extends IllegalArgumentException {
        public SameUsernameException() {
            super("Please choose a username different from your previous one.");
        }
    }

    public static class SameEmailException extends IllegalArgumentException {
        public SameEmailException() {
            super("Please choose a email different from your previous one.");
        }
    }

    public static class EmailAlreadyRegisteredException extends DuplicateKeyException {
        public EmailAlreadyRegisteredException() {
            super("Email already registered.");
        }
    }

    public static class UsernameAlreadyRegisteredException extends DuplicateKeyException {
        public UsernameAlreadyRegisteredException() {
            super("Username already registered.");
        }
    }
}

package com.marcosvcg.projeto.service;

import com.marcosvcg.projeto.exceptions.Exceptions;
import com.marcosvcg.projeto.model.User;
import com.marcosvcg.projeto.repository.UserRepository;
import com.marcosvcg.projeto.util.ChangeUserCredentials;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountManagementService implements ChangeUserCredentials {

    private final UserRepository userRepository;

    @Autowired
    public UserAccountManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User getExistingUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new Exceptions.UserNotFoundException(userId));
    }

    private boolean isSamePassword(User user, String password) {
        return (user.getPassword().equals(password));
    }
    private boolean isSameUsername(User user, String username) {
        return (user.getUsername().equals(username));
    }
    private boolean isSameEmail(User user, String email) {
        return (user.getEmail().equals(email));
    }

    @Override
    @Transactional
    public void changeUsername(Long userId, String newUsername) {
        User existingUser = getExistingUser(userId);
        if(isSameUsername(existingUser, newUsername)) {
            throw new Exceptions.SameUsernameException();
        }
        Optional<User> optionalUser = userRepository.findUserByUsername(newUsername);
        if(optionalUser.isPresent()) {
            throw new Exceptions.UsernameAlreadyRegisteredException();
        }
        existingUser.setUsername(newUsername);
        userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void changePassword(Long userId, String newPassword) {
        User existingUser = getExistingUser(userId);
        if(isSamePassword(existingUser, newPassword)) {
            throw new Exceptions.SamePasswordException();
        }
        existingUser.setPassword(newPassword);
        userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void changeEmail(Long userId, String newEmail) {
        // IMPLEMENTAR VERIFICACAO DE EMAIL!!!
        User existingUser = getExistingUser(userId);
        if(isSameEmail(existingUser, newEmail)) {
            throw new Exceptions.SameEmailException();
        }
        Optional<User> optionalUser = userRepository.findUserByEmail(newEmail);
        if(optionalUser.isPresent()) {
            throw new Exceptions.EmailAlreadyRegisteredException();
        }
        existingUser.setEmail(newEmail);
        userRepository.save(existingUser);
    }
}

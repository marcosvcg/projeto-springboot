package com.marcosvcg.projeto.service;

import com.marcosvcg.projeto.exceptions.Exceptions;
import com.marcosvcg.projeto.model.User;
import com.marcosvcg.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new Exceptions.UserNotFoundException(userId));
    }

    public void createUser(User user) {
        Optional<User> optionalUser = userRepository.findUserByUsername(user.getUsername());
        if(optionalUser.isPresent()) {
            throw new Exceptions.UsernameAlreadyRegisteredException();
        }
        optionalUser = userRepository.findUserByEmail(user.getEmail());
        if(optionalUser.isPresent()) {
            throw new Exceptions.EmailAlreadyRegisteredException();
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new Exceptions.UserNotFoundException(userId);
        }
        userRepository.deleteById(userId);
    }

}

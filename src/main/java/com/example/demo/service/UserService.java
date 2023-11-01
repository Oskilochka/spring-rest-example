package com.example.demo.service;

// Service is working with a logic, -> another abstraction layer

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registration(User user) throws UserAlreadyExistException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this username is exist!");
        } else {
            return userRepository.save(user);
        }
    }
}

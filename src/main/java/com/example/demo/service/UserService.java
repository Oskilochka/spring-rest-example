package com.example.demo.service;

// Service is working with a logic, -> another abstraction layer

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User getUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User was not found");
        }
        return user;
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

}

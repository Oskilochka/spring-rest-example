package com.example.demo.service;

// Service is working with a logic, -> another abstraction layer

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public UserModel getUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User was not found");
        }
        return UserModel.toModel(user);
    }

    public List<UserModel> getAll() {
        List<User> x = (List<User>) userRepository.findAll();

        return x.stream()
                .map(UserModel::toModel)
                .collect(Collectors.toList());
    }

    public Long deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return userId;
    }
}

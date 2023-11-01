package com.example.rest.service;

// Service is working with a logic, -> another abstraction layer

import com.example.rest.entity.User;
import com.example.rest.exceptions.UserAlreadyExistException;
import com.example.rest.exceptions.NotFoundException;
import com.example.rest.dto.UserDto;
import com.example.rest.mapper.UserMapper;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAll() {
        List<User> users = (List<User>) userRepository.findAll();

        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getById(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User was not found");
        }
        return userMapper.toDto(user.get());
    }

    public User create(User user) throws UserAlreadyExistException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this username is already exist!");
        } else {
            return userRepository.save(user);
        }
    }

    public boolean deleteById(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }
}

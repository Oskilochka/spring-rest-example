package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok("Server is working");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody User user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User was created successfully");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }
}

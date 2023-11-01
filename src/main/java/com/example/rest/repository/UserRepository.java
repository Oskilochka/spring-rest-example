package com.example.rest.repository;

import com.example.rest.entity.User;
import org.springframework.data.repository.CrudRepository;

// Repo is working with DB layer
public interface UserRepository extends CrudRepository<User, Long > {
    User findByUsername(String username);
}

package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepositoryImpl userRepository;

    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }


    public User greateNewUser(User user) {
        return userRepository.greateNewUser(user);
    }
}

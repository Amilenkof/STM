package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/greateNewUser")
    public User greateNewUser(@RequestBody User user ){
        return service.greateNewUser(user);
    }
}

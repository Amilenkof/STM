package com.example.demo.controller;

import com.example.demo.model.DTO.UserDtoIN;
import com.example.demo.model.DTO.UserDtoOut;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import com.example.demo.service.UserService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/greateNewUser")
    public ResponseEntity<UserDtoOut> greateNewUser(@RequestBody UserDtoIN userDtoIN) {
        return ResponseEntity.ok(service.greateNewUser(userDtoIN));
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserDtoOut> getUserById(@RequestParam("id") long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }
}
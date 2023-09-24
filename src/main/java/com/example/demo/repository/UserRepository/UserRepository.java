package com.example.demo.repository.UserRepository;

import com.example.demo.model.User;

import java.util.Optional;

public interface UserRepository {
    User findById(long id);
}

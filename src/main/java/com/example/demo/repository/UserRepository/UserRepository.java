package com.example.demo.repository.UserRepository;

import com.example.demo.model.User;

public interface UserRepository {
    User findById(long id);
}

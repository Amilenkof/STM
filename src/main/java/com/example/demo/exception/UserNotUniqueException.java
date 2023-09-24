package com.example.demo.exception;

public class UserNotUniqueException extends RuntimeException{
    public UserNotUniqueException(String message) {
        super(message);
    }
}

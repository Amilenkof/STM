package com.example.demo.exception;

public class ImpossibleCreateUserException extends RuntimeException{
    public ImpossibleCreateUserException(String message) {
        super(message);
    }
}

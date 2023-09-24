package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.DTO.UserDtoIN;
import com.example.demo.model.DTO.UserDtoOut;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import com.example.demo.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepositoryImpl userRepository;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(UserMapper.class);


    public UserService(UserRepositoryImpl userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDtoOut greateNewUser(UserDtoIN userDtoIN) {
        logger.info("Invoke UserService, method greateNewUser");

        try{
            User user = userMapper.toEntity(userDtoIN);
            userRepository.greateNewUser(user);
            return userMapper.toDTOOut(user);
        }catch (DataIntegrityViolationException e){
            throw new UserNotFoundException("User already have in DB");
        }
    }

    public UserDtoOut getUserById(long id) {
        logger.info("Invoke UserService, method getUserById");
        return userMapper.toDTOOut(
                userRepository.findById(id));
    }
}

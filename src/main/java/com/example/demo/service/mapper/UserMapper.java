package com.example.demo.service.mapper;

import com.example.demo.model.DTO.UserDtoIN;
import com.example.demo.model.DTO.UserDtoOut;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    private final Logger logger = LoggerFactory.getLogger(UserMapper.class);


    public User toEntity(UserDtoIN userDtoIN) {
        logger.info("Invoke UserMapper, method toEntity");

        return new User().builder()
                .login(userDtoIN.getLogin())
                .password(userDtoIN.getPassword())
                .fullName(userDtoIN.getFullName())
                .build();
    }

    public UserDtoOut toDTOOut(User user) {
        logger.info("Invoke UserMapper, method toDTOOut");

        return new UserDtoOut().builder()
                .login(user.getLogin())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .build();

    }
}

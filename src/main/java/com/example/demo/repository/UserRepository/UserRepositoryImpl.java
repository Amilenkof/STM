package com.example.demo.repository.UserRepository;

import com.example.demo.exception.ImpossibleCreateTicketException;
import com.example.demo.exception.ImpossibleCreateUserException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.transporterRepository.TransporterRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String FIND_BY_ID_SQL = """
            SELECT *
            FROM users
            WHERE id=? ;
            """;
    private final String ADD_NEW_USER = """
            INSERT INTO users (login,name,password)
            VALUES (?,?,?);
            """;
    private final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional()
    @Override
    public User findById(long id) {
        logger.info("Invoke UserRepositoryImpl, method findById= {}", id);
        if (id == 0) {
            return null;//todo бредово- если нашел вернет если нет null- ошибки вообще никогда не будет
        }
        return (jdbcTemplate.queryForStream(FIND_BY_ID_SQL, (rs, row) -> {
            new User();
            return User.builder()
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .id(id)
                    .fullName(rs.getString("name"))
                    .build();
        }, id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("The specified user was not found"));

    }

    @Transactional()
    public User greateNewUser(User user) {
        logger.info("Invoke UserRepositoryImpl, method greateNewUser");

        int update = jdbcTemplate.update(ADD_NEW_USER, user.getLogin(), user.getFullName(), user.getPassword());
        if (update != 0) {
            return user;
        } else throw new ImpossibleCreateUserException("It is not possible to create the specified ticket");

    }
}
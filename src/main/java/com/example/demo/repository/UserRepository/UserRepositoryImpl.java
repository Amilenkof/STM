package com.example.demo.repository.UserRepository;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private JdbcTemplate jdbcTemplate;
    private final String FIND_BY_ID = """
            SELECT *
            FROM users
            WHERE id=? ;
            """;
    private final String ADD_NEW_USER = """
            INSERT INTO users (login,name,password)
            VALUES (?,?,?);
            """;
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(long id) {
       return jdbcTemplate.query(FIND_BY_ID,rs -> {
            User user = new User().builder()
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .id(id)
                    .fullName(rs.getString("name"))
                    .build();
            return user;
        });

    }

    public User greateNewUser(User user) {
        int update = jdbcTemplate.update(ADD_NEW_USER, user.getLogin(), user.getFullName(), user.getPassword());
        if (update!=0){
            return user;
        } else throw new IllegalArgumentException();//todo

    }
}
//public int save(Employee e) {
//    return jdbcTemplate.update("INSERT INTO tbl_employees (name, location, department) VALUES (?, ?, ?)", new Object[] {e.getName(), e.getLocation(), e.getDepartment()});
package com.example.session9.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.session9.model.Users;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int saveUser(String name, String email) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try {
            jdbcTemplate.update(sql, name, email);
        } catch (DataAccessException e) {
            // Handle exception
        }

        return jdbcTemplate.update(sql, name, email);
    }

    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Transactional
    public void saveUserWithTransaction(String name, String email) {
        jdbcTemplate.update("INSERT INTO users (name, email) VALUES (?, ?)", name, email);
    }

}

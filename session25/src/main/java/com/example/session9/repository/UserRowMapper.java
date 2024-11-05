package com.example.session9.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.session9.model.Users;

public class UserRowMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users user = new Users();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        return user;
    }
    
}



package com.example.session36.components;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.session36.model.MyEntity;

public class MyRowMapper implements RowMapper<MyEntity> {
    @Override
    public MyEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        MyEntity entity = new MyEntity();
        entity.setId(rs.getInt("id"));
        entity.setName(rs.getString("name"));
        return entity;
    }
}

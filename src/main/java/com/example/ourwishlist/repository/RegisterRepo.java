package com.example.ourwishlist.repository;

import com.example.ourwishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterRepo {

    @Autowired
    JdbcTemplate template;

    public void registerNewUser(User user) {
        String sql = "INSERT INTO accounts (username, passwd, first_name, last_name, email, address) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPasswd(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getAddress());

    }
}

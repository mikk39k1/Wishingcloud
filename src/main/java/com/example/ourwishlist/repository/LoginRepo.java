package com.example.ourwishlist.repository;

import com.example.ourwishlist.model.LoginRequest;
import com.example.ourwishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginRepo {

    @Autowired
    JdbcTemplate template;

//    public Map<User, Boolean> loginUser(String username, String passwd) {
//        String sql = "SELECT * FROM accounts WHERE username = ? AND passwd = ?";
//        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
//        Map<User, Boolean> loginResult = new HashMap<>();
//        User user = null;
//        boolean success = false;
//        try {
//            user = template.queryForObject(sql,rowMapper, username, passwd);
//            success = true;
//        } catch (EmptyResultDataAccessException ex){
//            System.out.println("Exception: " + ex);
//        }
//        loginResult.put(user,success);
//        return loginResult;
//    }


    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM accounts WHERE username = ?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class), username);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    public LoginRequest.LoginResult loginUser(String username, String passwd) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND passwd = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = null;
        try {
            user = template.queryForObject(sql,rowMapper, username, passwd);
            return LoginRequest.LoginResult.SUCCESS;
        } catch (EmptyResultDataAccessException ex){
            System.out.println("Exception: " + ex);
            // Check if username is correct
            User existingUser = getUserByUsername(username);
            return existingUser == null ? LoginRequest.LoginResult.INCORRECT_USERNAME : LoginRequest.LoginResult.INCORRECT_PASSWORD;
        }
    }

}

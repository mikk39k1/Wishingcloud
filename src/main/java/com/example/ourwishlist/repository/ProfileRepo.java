package com.example.ourwishlist.repository;

import com.example.ourwishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepo {

    @Autowired
    JdbcTemplate template;


    public void editUser(int accountsId, User user) {
        String sql = "UPDATE accounts SET username = ?, passwd = ?, first_name = ?, last_name = ?, " +
                "email = ?, address = ? WHERE accounts_id = ?";
        template.update(sql, user.getUsername(), user.getPasswd(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getAddress(), accountsId);
    }
}

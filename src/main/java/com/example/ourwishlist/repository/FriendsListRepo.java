package com.example.ourwishlist.repository;

import com.example.ourwishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendsListRepo {
    @Autowired
    JdbcTemplate template;

    public List<User> getFriends(int accountId) {
        String sql = "SELECT DISTINCT * FROM accounts" +
                " WHERE accounts_id IN (" +
                "SELECT" +
                " CASE" +
                " WHEN accounts_id = ? THEN friend_id" +
                "    WHEN friend_id = ? THEN accounts_id" +
                " END" +
                " FROM friend_relation" +
                " WHERE accounts_id = ? OR friend_id = ?" +
                ");";
        RowMapper<User> friendsListMap = new BeanPropertyRowMapper<>(User.class);
        return template.query(sql,friendsListMap, accountId, accountId, accountId, accountId);
    }


}
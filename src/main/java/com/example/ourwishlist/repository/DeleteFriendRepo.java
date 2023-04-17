package com.example.ourwishlist.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteFriendRepo {

    @Autowired
    JdbcTemplate template;

    public void deleteFriend(int accountId, int friendId) {
        String sql = "DELETE FROM friend_relation " +
                "WHERE (accounts_id = ? AND friend_id = ?) " +
                "OR (accounts_id = ? AND friend_id = ?)";
        template.update(sql,accountId, friendId, friendId,accountId);
    }
}

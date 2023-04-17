package com.example.ourwishlist.repository;

import com.example.ourwishlist.model.Item;
import com.example.ourwishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendWishListRepository {
    @Autowired
    JdbcTemplate template;

    public List<Item> getFriendWishes(int friendAccountId) {
        String sql = "SELECT * FROM wishentity WHERE accounts_id = ?;";
        RowMapper<Item> itemRowMapper = new BeanPropertyRowMapper<>(Item.class);
        return template.query(sql, itemRowMapper, friendAccountId);
    }

    public void reserveFriendWish(int entitityId, int reserveAccountId) {
        String sql = "UPDATE wishentity SET reserve_account_id = ? WHERE wishentity_id = ?;";
        template.update(sql, reserveAccountId, entitityId);
    }

    public void unReserveFriendWish(int entitityId) {
        String sql = "UPDATE wishentity SET reserve_account_id = ? WHERE wishentity_id = ?;";
        template.update(sql, null, entitityId);
    }

    public User getFriend(int accountId) {
        String sql = "SELECT * FROM accounts WHERE accounts_id = ?";
        RowMapper<User> friendsListMap = new BeanPropertyRowMapper<>(User.class);
        return template.query(sql,friendsListMap, accountId).get(0);
    }
}

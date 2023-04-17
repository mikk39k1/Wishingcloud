package com.example.ourwishlist.repository;

import com.example.ourwishlist.model.FriendRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class FriendRequestRepo {

    @Autowired
    JdbcTemplate template;


    public void sendFriendRequest(int accountsId, int friendId) {
        String sql = "INSERT INTO friend_request VALUES (?,?)";
        template.update(sql,accountsId, friendId);
    }

    public List<FriendRequestStatus> showFriendRequests(int accountsId) {
        String sql = "SELECT a.*, \n" +
                "       CASE\n" +
                "           WHEN fr.sender_id = ? THEN 'sent'\n" +
                "           ELSE 'received'\n" +
                "       END as friend_request_status\n" +
                "FROM accounts a\n" +
                "JOIN (\n" +
                "    SELECT accounts_id AS sender_id, friend_id AS receiver_id\n" +
                "    FROM friend_request\n" +
                "    WHERE accounts_id = ? OR friend_id = ?\n" +
                ") fr\n" +
                "ON a.accounts_id = fr.sender_id OR a.accounts_id = fr.receiver_id\n" +
                "WHERE a.accounts_id <> ?;";
        return template.query(sql, new BeanPropertyRowMapper<>(FriendRequestStatus.class), accountsId, accountsId, accountsId, accountsId);
    }


    public void acceptRequest(int friendId, int accountsId) {
        String sql = "INSERT INTO friend_relation VALUES (?,?)";
        template.update(sql, accountsId, friendId);
    }

    public void deleteRequest(int accountsId, int friendId) {
        String sql = "DELETE FROM friend_request " +
                "WHERE (accounts_id = ? AND friend_id = ?) " +
                "OR (accounts_id = ? AND friend_id = ?) ";
        template.update(sql, accountsId, friendId, friendId, accountsId);
    }


}

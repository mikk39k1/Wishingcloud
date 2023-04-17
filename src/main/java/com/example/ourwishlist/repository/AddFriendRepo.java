package com.example.ourwishlist.repository;

import com.example.ourwishlist.model.FriendRequestStatus;
import com.example.ourwishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AddFriendRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> searchUsersByName(String name) {
        String sql = "SELECT * FROM accounts WHERE first_name LIKE ? OR last_name LIKE ?";
        if (name.contains(" ")) {
            String[] names = name.split(" ");
            if (names.length > 2) {
                String first_name = "%" + names[0] + "%";
                String last_name = "%" +
                        Arrays.stream(names).skip(1).collect(Collectors.joining(" ")) + "%";
                return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), first_name, last_name);
            } else {
                return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),
                        ("%" + names[0] + "%"), ("%" + names[1] + "%"));
            }
        }
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), ("%" + name + "%"),
                ("%" + name + "%"));
    }

    public List<FriendRequestStatus> showFriendRequests(int currentUserAccountsId) {
        String sql = "SELECT a.accounts_id, first_name, last_name,\n" +
                "       CASE\n" +
                "           WHEN fr.sender_id = :id THEN 'sent'\n" +
                "           WHEN fr.receiver_id = :id THEN 'received'\n" +
                "           WHEN fr_rel.accounts_id IS NOT NULL THEN 'friends'\n" +
                "           ELSE 'none'\n" +
                "       END as friend_request_status\n" +
                "FROM accounts a\n" +
                "LEFT JOIN (\n" +
                "    SELECT accounts_id AS sender_id, friend_id AS receiver_id\n" +
                "    FROM friend_request\n" +
                "    WHERE accounts_id = :id OR friend_id = :id\n" +
                ") fr\n" +
                "ON a.accounts_id = fr.sender_id OR a.accounts_id = fr.receiver_id\n" +
                "LEFT JOIN (\n" +
                "    SELECT accounts_id, friend_id\n" +
                "    FROM friend_relation\n" +
                ") fr_rel\n" +
                "ON (a.accounts_id = fr_rel.accounts_id AND fr_rel.friend_id = :id) OR (a.accounts_id = fr_rel.friend_id AND fr_rel.accounts_id = :id)\n" +
                "WHERE a.accounts_id <> :id;\n";
        Map<String, Integer> namedParameters = new HashMap<>();
        namedParameters.put("id", currentUserAccountsId);
        return namedParameterJdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<>(FriendRequestStatus.class));
    }
}

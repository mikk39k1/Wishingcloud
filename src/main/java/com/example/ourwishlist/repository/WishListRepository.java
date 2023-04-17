package com.example.ourwishlist.repository;

import com.example.ourwishlist.model.Item;
import com.example.ourwishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

@Repository
public class WishListRepository {
    @Autowired
    JdbcTemplate template;

    public List<Item> getItems(int accountId) {
        String sql = "SELECT * FROM wishentity WHERE accounts_id = ?;";
        RowMapper<Item> albumRowMapper = new BeanPropertyRowMapper<>(Item.class);
        return template.query(sql, albumRowMapper, accountId);
    }

    public void addWishItem(Item item, User user) {
        String sql = "INSERT INTO wishentity (entity_name, entity_amount, accounts_id) VALUES (?, ?, ?);";

        template.update(sql, item.getEntityName(), item.getEntityAmount(), user.getAccountsId());

    }
    public void deleteWishItem(int wishentityId) {
        String sql = "DELETE FROM wishentity WHERE wishentity_id = ?;";
        template.update(sql, wishentityId);
    }
    public void updateWish(int enitityId, String entityName, int entityAmount) {
        String sql = "UPDATE wishentity SET entity_name = ?, entity_amount = ? WHERE wishentity_id = ?;";
        template.update(sql, entityName, entityAmount, enitityId);
    }
    public void updateWishEntityName(int enitityId, String entityName) {
        String sql = "UPDATE wishentity SET entity_name = ? WHERE wishentity_id = ?;";
        template.update(sql, entityName, enitityId);
    }

    public void updateWishEntityAmount(int enitityId, int entityAmount) {
        String sql = "UPDATE wishentity SET entity_amount = ? WHERE wishentity_id = ?;";
        template.update(sql, entityAmount, enitityId);
    }
}

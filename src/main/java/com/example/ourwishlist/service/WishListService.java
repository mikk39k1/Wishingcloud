package com.example.ourwishlist.service;

import com.example.ourwishlist.model.Item;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;

    public User getLoggedInUser() {
        return null;
    }

    public List<Item> getItems(int accountId) {
        return wishListRepository.getItems(accountId);
    }

    public void addWishItem(Item item, User user) {
        wishListRepository.addWishItem(item, user);
    }

    public void updateWish(int entityId, String entityName, int entityAmount) {
        wishListRepository.updateWish(entityId, entityName, entityAmount);
    }
    public void updateWishEntityName(int entityId, String entityName) {
        wishListRepository.updateWishEntityName(entityId, entityName);
    }

    public void updateWishEntiAmount(int entityId, int entityAmount) {
        wishListRepository.updateWishEntityAmount(entityId, entityAmount);
    }

    public void deleteWishItem(int wishentityId) {
        wishListRepository.deleteWishItem(wishentityId);
    }
}

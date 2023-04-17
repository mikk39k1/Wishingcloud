package com.example.ourwishlist.service;

import com.example.ourwishlist.model.Item;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.repository.FriendWishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendWishListService {
    @Autowired
    FriendWishListRepository friendWishListRepository;

    public List<Item> getFriendWishes(int friendAccountId) {
        return friendWishListRepository.getFriendWishes(friendAccountId);
    }

    public void reserveFriendWish(int entitityId, int reserveAccountId) {
        friendWishListRepository.reserveFriendWish(entitityId, reserveAccountId);
    }

    public void unReserveFriendWish(int entitityId) {
        friendWishListRepository.unReserveFriendWish(entitityId);
    }

    public User getFriend(int accountId){
        return friendWishListRepository.getFriend(accountId);
    }
}

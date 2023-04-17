package com.example.ourwishlist.service;

import com.example.ourwishlist.model.User;
import com.example.ourwishlist.repository.FriendsListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FriendsListService {
    @Autowired
    FriendsListRepo friendsListRepo;

    public List<User> getFriends(int accountId){
        return friendsListRepo.getFriends(accountId);
    }

}

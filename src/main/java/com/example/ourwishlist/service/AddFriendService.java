package com.example.ourwishlist.service;

import com.example.ourwishlist.model.FriendRequestStatus;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.repository.AddFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddFriendService {

    @Autowired
    AddFriendRepo addFriendRepo;


    public List<User> searchUsersByName(String name) {
        return addFriendRepo.searchUsersByName(name);
    }

    public List<FriendRequestStatus> showFriendRequests(int currentUserAccountsId) {
        return addFriendRepo.showFriendRequests(currentUserAccountsId);
    }



}

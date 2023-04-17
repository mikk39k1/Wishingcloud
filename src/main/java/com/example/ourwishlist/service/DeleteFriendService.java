package com.example.ourwishlist.service;

import com.example.ourwishlist.repository.DeleteFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteFriendService {


    @Autowired
    DeleteFriendRepo deleteFriendRepo;

    public void deleteFriend(int accountId, int friendId) {
        deleteFriendRepo.deleteFriend(accountId, friendId);
    }
}

package com.example.ourwishlist.service;

import com.example.ourwishlist.model.FriendRequestStatus;
import com.example.ourwishlist.repository.FriendRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {

    @Autowired
    FriendRequestRepo friendRequestRepo;

    public void sendFriendRequest(int accountsId, int friendId) {
        friendRequestRepo.sendFriendRequest(accountsId, friendId);
    }

    public List<FriendRequestStatus> showFriendRequests(int accountsId) {
        return friendRequestRepo.showFriendRequests(accountsId);
    }

    public void acceptRequest(int friendId, int accountsId) {
        friendRequestRepo.acceptRequest(friendId, accountsId);
    }

    public void deleteRequest(int accountsId, int friendId) {
        friendRequestRepo.deleteRequest(accountsId, friendId);
    }

}

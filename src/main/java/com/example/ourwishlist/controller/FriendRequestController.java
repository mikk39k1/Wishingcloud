package com.example.ourwishlist.controller;

import com.example.ourwishlist.model.FriendRequestStatus;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.service.FriendRequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FriendRequestController {

    @Autowired
    FriendRequestService friendRequestService;

    @PostMapping("/addFriendAction")
    public String sendFriendRequest(HttpSession session, @RequestParam("friendId")int friendId){
        User user = (User) session.getAttribute("user");
        friendRequestService.sendFriendRequest(user.getAccountsId(), friendId);
        return "redirect:/addFriend";
    }

    @GetMapping("/friendrequest")
    public String showFriendRequests(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<FriendRequestStatus> friendrequests =  friendRequestService.showFriendRequests(user.getAccountsId());
        model.addAttribute("requests", friendrequests);
        return "/friendrequest";
    }

    @PostMapping("/acceptRequest")
    public String acceptRequest(HttpSession session, @RequestParam("friendId")int friendId){
        User user = (User) session.getAttribute("user");
        friendRequestService.acceptRequest(friendId, user.getAccountsId());
        friendRequestService.deleteRequest(user.getAccountsId(), friendId);
        return "redirect:/friendrequest";
    }
    @PostMapping("/deleteRequest")
    public String deleteRequest(@RequestParam("friendId") int friendId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        friendRequestService.deleteRequest(user.getAccountsId(), friendId);
        return "redirect:/friendrequest";
    }


}

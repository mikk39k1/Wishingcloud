package com.example.ourwishlist.controller;

import com.example.ourwishlist.model.User;
import com.example.ourwishlist.service.DeleteFriendService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteFriendController {

    @Autowired
    DeleteFriendService deleteFriendService;

    @GetMapping("/deleteFriend")
    public String deleteFriend(@RequestParam("friendId") int friendId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        deleteFriendService.deleteFriend(user.getAccountsId(), friendId);
        return "redirect:/addFriend";
    }
}

package com.example.ourwishlist.controller;

import com.example.ourwishlist.model.FriendRequestStatus;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.service.AddFriendService;
import com.example.ourwishlist.service.FriendRequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class AddFriendController {

    @Autowired
    AddFriendService addFriendService;

    @Autowired
    FriendRequestService friendRequestService;

    @GetMapping("/addFriend")
    public String addFriend(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        return "/addFriend";
    }


    @PostMapping("/searchFriend")
    public String searchFriend(@RequestParam("friendName") String friendName, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);

        List<User> foundUsers = addFriendService.searchUsersByName(friendName).stream()
                .filter(foundUser -> !Objects.equals(foundUser.getAccountsId(), user.getAccountsId())).toList();

        List<FriendRequestStatus> friendRequestStatuses = foundUsers.stream()
                .flatMap(account -> addFriendService.showFriendRequests(user.getAccountsId()).stream()
                        .filter(friendRequestStatus -> Objects.equals(friendRequestStatus.getAccountsId(), account.getAccountsId())))
                .toList();

        model.addAttribute("searchedName", friendName);
        model.addAttribute("friendsList", friendRequestStatuses);
        return "addFriend";
    }




}

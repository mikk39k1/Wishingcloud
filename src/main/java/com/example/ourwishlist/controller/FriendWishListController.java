package com.example.ourwishlist.controller;

import com.example.ourwishlist.model.Item;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.service.FriendWishListService;
import com.example.ourwishlist.service.FriendsListService;
import com.example.ourwishlist.service.WishListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FriendWishListController {
    @Autowired
    FriendWishListService friendWishListService;
    @Autowired
    FriendsListService friendsListService;
    @GetMapping("/friendWishList/{friendId}")
    public String friendWishList(Model model, @PathVariable("friendId") int friendId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        List<Item> friendWishes = friendWishListService.getFriendWishes((friendId));
        model.addAttribute("friendWishList", friendWishes);
        model.addAttribute("friend", friendWishListService.getFriend(friendId));
        model.addAttribute("userModel", httpSession.getAttribute("user"));
        model.addAttribute("friendsList",friendsListService.getFriends(user.getAccountsId()));
        return "/friendWishList";
    }

    @PostMapping("/reserveWish")
    public String reserveWish(@RequestParam("wishentityId") int wishentityId, @RequestParam("friendId") int friendId, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        User user = (User) httpSession.getAttribute("user");
        friendWishListService.reserveFriendWish(wishentityId, user.getAccountsId());
        redirectAttributes.addAttribute("friendId", friendId);
        return "redirect:/friendWishList/{friendId}";
    }


    @PostMapping("/unReserveWish")
    public String unReserveWish(@RequestParam("wishentityId") int wishentityId, @RequestParam("friendId") int friendId, RedirectAttributes redirectAttributes) {
        friendWishListService.unReserveFriendWish(wishentityId);
        redirectAttributes.addAttribute("friendId", friendId);
        return "redirect:/friendWishList/{friendId}";
    }
}

package com.example.ourwishlist.controller;

import com.example.ourwishlist.model.Item;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.service.FriendsListService;
import com.example.ourwishlist.service.WishListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class HomepageController {
    @Autowired
    WishListService wishListService;
    @Autowired
    FriendsListService friendsListService;

    @GetMapping("/")
    public String landingScreen() {
        return "home/landingscreen";
    }


    @GetMapping("/homepage")
    public String landingPage(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("wishList", wishListService.getItems(user.getAccountsId()));
        model.addAttribute("friendsList",friendsListService.getFriends(user.getAccountsId()));
        return "homepage";
    }

    @PostMapping("/createNewWish")
    public String createNewWish(@ModelAttribute Item item, HttpSession httpSession) {
        if (item.getEntityName().length() <= 50 && item.getEntityAmount() <= 100) {
            User user = (User) httpSession.getAttribute("user");
            wishListService.addWishItem(item, user);
        }
        return "redirect:/homepage";
    }

    @PostMapping("/updateWish")
    public String updateWish(@RequestParam("wishentityId") int wishentityId,
                             @RequestParam("entityName") String entityName,
                             @RequestParam("entityAmount") String entityAmount) {
        int setEntityAmount = convertEntityAmountToInt(entityAmount);
        System.out.println("ENTITY amount = " + setEntityAmount);

        if (!entityName.isEmpty() && setEntityAmount != 0) {
            wishListService.updateWish(wishentityId, entityName, setEntityAmount);
        } else if (!entityName.isEmpty()) {
            wishListService.updateWishEntityName(wishentityId, entityName);
        } else if (setEntityAmount != 0) {
            wishListService.updateWishEntiAmount(wishentityId, setEntityAmount);
        }
        return "redirect:/homepage";
    }

    @PostMapping("/deleteWish")
    public String deleteWish(@RequestParam("wishentityId") int wishentityId) {
        wishListService.deleteWishItem(wishentityId);
        return "redirect:/homepage";
    }


    private int convertEntityAmountToInt(String entityAmount) {
        try {
            return Integer.parseInt(entityAmount);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe);
        }
        return 0;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}

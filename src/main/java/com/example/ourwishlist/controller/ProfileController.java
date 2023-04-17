package com.example.ourwishlist.controller;

import com.example.ourwishlist.model.User;
import com.example.ourwishlist.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/profile";
    }

    @GetMapping("/editprofile")
    public String editProfile(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "/editprofile";
    }

    @PostMapping("/editUser")
    public String editUser(HttpSession session, @ModelAttribute User modeluser){
        User user = (User) session.getAttribute("user");
        profileService.editUser(user.getAccountsId(), modeluser);
        return "/profile";
    }


}

package com.example.ourwishlist.controller;

import com.example.ourwishlist.model.User;
import com.example.ourwishlist.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    RegisterService registerService;


    @GetMapping("authorization/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "authorization/register";
    }

    @PostMapping("/registerNewUser")
    public String registerNewUser(@ModelAttribute("user") @Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "authorization/register";
        }
        registerService.registerNewUser(user);
        return "redirect:/authorization/login";
    }

}

package com.example.ourwishlist.controller;

import com.example.ourwishlist.model.LoginRequest;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("authorization/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "authorization/login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, HttpSession session) {
        LoginRequest.LoginResult loginResult = loginService.loginRequest(user.getUsername(), user.getPasswd());

        switch (loginResult) {
            case SUCCESS -> {
                User userLoggedIn = loginService.getUserByName(user.getUsername());
                model.addAttribute("user", userLoggedIn);
                session.setAttribute("user", userLoggedIn);
                return "redirect:/homepage";
            }
            case INCORRECT_USERNAME -> result.rejectValue("username", "error.login", "Incorrect username");
            case INCORRECT_PASSWORD -> result.rejectValue("passwd", "error.login", "Incorrect password");
        }
        return "authorization/login";
    }



    @GetMapping("/accessDenied")
    public String accessDenied(HttpServletResponse response) {
        // Set no cache headers
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies

        return "authorization/accessDenied";
    }
}

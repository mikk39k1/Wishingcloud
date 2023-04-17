package com.example.ourwishlist.service;

import com.example.ourwishlist.model.LoginRequest;
import com.example.ourwishlist.model.User;
import com.example.ourwishlist.repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {

    @Autowired
    LoginRepo loginRepo;


//    public Map<User, Boolean> loginUser(String username, String passwd) {
//       return loginRepo.loginUser(username,passwd);
//    }


    public LoginRequest.LoginResult loginRequest(String username, String passwd) {
        return loginRepo.loginUser(username, passwd);
    }

    public User getUserByName(String username) {
        return loginRepo.getUserByUsername(username);
    }
}

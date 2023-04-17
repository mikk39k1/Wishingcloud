package com.example.ourwishlist.service;

import com.example.ourwishlist.model.User;
import com.example.ourwishlist.repository.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    RegisterRepo registerRepo;

    public void registerNewUser(User user) {
        registerRepo.registerNewUser(user);
    }
}

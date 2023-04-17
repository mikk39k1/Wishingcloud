package com.example.ourwishlist.service;

import com.example.ourwishlist.model.User;
import com.example.ourwishlist.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepo profileRepo;

    public void editUser(int accountsId, User user) {
        profileRepo.editUser(accountsId, user);
    }
}

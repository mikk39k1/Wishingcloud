package com.example.ourwishlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FriendRequestStatus {
    private int accountsId;
    private String firstName;
    private String lastName;
    private String friendRequestStatus;
}

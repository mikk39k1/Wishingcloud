package com.example.ourwishlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class Item {
    private String wishentityId;
    private String entityName;
    private double entityAmount;
    private int accountsId;
    private Integer reserveAccountId;

}

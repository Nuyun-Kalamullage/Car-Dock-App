package com.example.cardock.backend;

import java.util.HashMap;

public class seller extends user{
    public static HashMap<String,car> registeredCarsArray =car.registeredCarsArray;

    public seller(String name, String email, String userName, int password, String nic, String contactNumber, String imageurl) {
        super(name, email, userName, password, nic, contactNumber, imageurl);
    }

    public void editAd(){

    }
    public void removeAd(){

    }
    public void viewWishlist(){

    }
}

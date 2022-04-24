package com.example.cardock.backend;

import java.util.ArrayList;
import java.util.HashMap;

public class buyer extends user{

    protected static ArrayList<car> carArrayList = car.carArrayList;
    private HashMap<String,wishlist> carWishList = new HashMap<>();

    public buyer(String name, String email, String userName, int password, String nic, String contactNumber, String imageurl) {
        super(name, email, userName, password, nic, contactNumber, imageurl);
    }

    public void viewCars(){

    }
    public ArrayList<car> searchCar(){
        ArrayList<car> filteredCars = new ArrayList<>();
        return filteredCars;
    }
    public void removeWishList(){

    }
}

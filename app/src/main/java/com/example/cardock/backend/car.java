package com.example.cardock.backend;

import android.widget.ImageView;

import java.util.Date;
import java.util.HashMap;

public class car {
    private ImageView front;
    private ImageView back;
    private ImageView interior;
    private ImageView side;
    private String model;
    private String year;
    private String millage;
    private String price;
    private String description;
    private String fuelType;
    private boolean finance;

    public static HashMap<String,car> registeredCarsArray = new HashMap<>();
    private String sellerName;
    private Date datePublished;
    private int likesCount;


    public car(String name, ImageView front, ImageView back, ImageView interior, ImageView side, String model, String year, String millage, String price, String description,int fueltype,boolean finance) {
        this.sellerName = name;
        this.front = front;
        this.back = back;
        this.interior = interior;
        this.side = side;
        this.model = model;
        this.year = year;
        this.millage = millage;
        this.price = price;
        this.description = description;
        switch (fueltype){
            case 0:
                this.fuelType = "Petrol";
                break;
            case 1:
                this.fuelType = "Diesel";
                break;
            case 2:
                this.fuelType = "Electric";
                break;
        }
        this.finance = finance;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public static boolean RegisterNewCar(String name, ImageView front, ImageView back, ImageView interior, ImageView side, String model, String year, String millage, String price, String description,int fueltype,boolean finance){
        if(!registeredCarsArray.containsKey(name)){
            registeredCarsArray.put(name, new car( name,front, back, interior, side, model, year, millage, price, description,fueltype,finance));
            return true;
        }
        else{
            return false;
        }
    }

}

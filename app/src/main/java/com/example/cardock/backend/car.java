package com.example.cardock.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class car {
    private String front ;
    private String rear;
    private String interior;
    private String side;
    private String model;
    private String year;
    private String millage;
    private String price;
    private String description;
    private String fuelType;
    private String finance;
    public static ArrayList<car> carArrayList = new ArrayList<>();

    public static HashMap<String,car> registeredCarsArray = new HashMap<>();
    private String sellerName;
    private Date datePublished;
    private int likesCount;

    public car(String name, String front, String rear, String interior, String side, String model, String year, String millage, String price, String description, int fueltype, boolean finance) {
        this.sellerName = name;
        this.front = front;
        this.rear = rear;
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
        if (finance)
            this.finance = "yes";
        else
            this.finance = "no";
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getMillage() {
        return millage;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getFinance() {
        return finance;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public static boolean RegisterNewCar(String name, String front, String back, String interior, String side, String model, String year, String millage, String price, String description,int fueltype,boolean finance){
        if(!registeredCarsArray.containsKey(name)){
            car newCar = new car( name,front, back, interior, side, model, year, millage, price, description,fueltype,finance);
            registeredCarsArray.put(name, newCar);
            carArrayList.add(newCar);
            return true;
        }
        else{
            return false;
        }
    }

    public String[] getCarImageArray() {
       return new String[]{front, rear, interior, side};

    }
}

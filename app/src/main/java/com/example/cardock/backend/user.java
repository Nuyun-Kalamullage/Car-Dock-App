package com.example.cardock.backend;

import java.util.HashMap;

public class user {
    private String name;
    private String email;
    private String userName;
    private int password;
    private String imageUri;
    private String nic;
    private String contactNumber;
    public static HashMap<String,user> registeredUserArray = new HashMap<>();

    public user(String name, String email, String userName, int password, String nic, String contactNumber ,String imageurl) {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.nic = nic;
        this.contactNumber = contactNumber;
        this.imageUri = imageurl;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public String getImageUri() {
        return imageUri;
    }

    public static boolean RegisterNewUser(String name, String email, String userName, int password, String nic, String contactNumber, String imageUrl){
        if(!registeredUserArray.containsKey(userName)){
            registeredUserArray.put(userName, new user( name,  email, userName, password, nic, contactNumber,imageUrl));
            return true;
        }
        else{
            return false;
        }
    }
    public static int authoriseLogin(String userName, int password){
        if (registeredUserArray.containsKey(userName)){
            if (registeredUserArray.get(userName).getPassword() == password){
                return 1;
            }else return 0;
        }else return -1;
    }

}

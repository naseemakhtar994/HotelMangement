package com.example.hotelmanagement.model;

public class UserModel {
    public  String name;
    public String email;
    public String mobile;
    public String password;

    public UserModel(String name, String email, String mobile, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public UserModel() {

    }
}

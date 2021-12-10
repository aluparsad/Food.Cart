package com.icmi.foodcart.Model;

public class User {

    private String
            name,
            userId,
            number,
            address;

    public User() {
    }

    public User(String name, String userId, String number, String address) {
        this.name = name;
        this.userId = userId;
        this.number = number;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package com.pluralsight.dealership.models;

import java.util.ArrayList;

public class Dealership {
    private int id;
    private String name;
    private String address;
    private String phone;

    //Constructor for a dealership with only an id
    public Dealership(int id) {
        this.id = id;
    }

    //Constructor to create a new Dealership object
    public Dealership(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-8s %-30s %-20s", id, name, address, phone);
    }
}

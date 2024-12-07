package com.pluralsight.dealership.models;

import java.util.ArrayList;

public class Dealership {
    private int id;
    private String name;
    private String address;
    private String phone;
    private final ArrayList<Vehicle> inventory = new ArrayList<>();

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

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-12s %-15s %-12s", id, name, address, phone);
    }
}

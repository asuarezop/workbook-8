package com.pluralsight.dealership.models;

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
        return String.format("%-5d %-19s %-36s %-19s", id, name, address, phone);
    }
}

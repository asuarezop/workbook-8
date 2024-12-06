package com.pluralsight.dealership.models;

public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int miles;
    private double price;
    private boolean sold;

    //Constructing a vehicle object with only a VIN
    public Vehicle(int vin) {
        this.vin = vin;
    }

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int miles, double price, boolean sold) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.miles = miles;
        this.price = price;
        this.sold = sold;
    }

    //Getters
    public int getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public int getMiles() {
        return miles;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSold() {
        return sold;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-8s %-15s %-13s %-17s %-10s %-12d %5.2f %8s", vin, year, make, model, vehicleType, color, miles, price, (sold) ? "YES" : "NO");
    }
}

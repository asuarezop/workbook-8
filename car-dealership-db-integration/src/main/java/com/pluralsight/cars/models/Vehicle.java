package com.pluralsight.cars.models;

public class Vehicle {
    private final int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    //Constructing a vehicle object with only a VIN
    public Vehicle(int vin) {
        this.vin = vin;
    }

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
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

    public int getOdometer() {
        return odometer;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-8s %-15s %-13s %-17s %-10s %-12d %5.2f", vin, year, make, model, vehicleType, color, odometer, price);
    }

    public String toCSV() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%.2f\n", vin, year, make, model, vehicleType, color, odometer, price);
    }
}

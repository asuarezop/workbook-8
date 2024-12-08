package com.pluralsight.dealership.models;

import java.time.LocalDate;

//Abstract Contract Class - cannot be used to instantiate a new object
public abstract class Contract {
    private int id;
    private LocalDate date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private int vehicleVin;

    //For application representation
    protected Contract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    //For database representation
    protected Contract(int id, int vehicleVin) {
        this.id = id;
        this.vehicleVin = vehicleVin;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public int getVehicleVin() {
        return vehicleVin;
    }

    //Subclasses have to provide their own implementation of each of these abstract methods
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    @Override
    public abstract String toString();
}

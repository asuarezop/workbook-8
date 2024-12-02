package com.pluralsight.cars.models;
import JavaHelpers.ColorCodes;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private final ArrayList<Vehicle> inventory = new ArrayList<>();

    //Blank constructor for when values aren't known
    public Dealership() {}

    //Constructor to create a new Dealership object
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    //Getters & Setters
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

    //Non-static methods for Dealership processing requests
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v: inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v: inventory) {
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByYear(int year) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v: inventory) {
            if (v.getYear() == year) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v: inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v: inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByVehicleType(String vehicleType) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v: inventory) {
            if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v: inventory) {
            results.add(v);
        }
        return results;
    }

    public void addVehicle(Vehicle v) {
        inventory.add(v);
        //Confirmation message
        System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Vehicle was added to current inventory!" + ColorCodes.RESET);
    }

    public void removeVehicle(Vehicle v) {
        //Removing vehicle if it matches VIN inside current dealership inventory
        inventory.removeIf(c -> c.getVin() == v.getVin());
        //Confirmation message
        System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Vehicle removed from dealership." + ColorCodes.RESET);
    }

    public Vehicle getVehiclesByVin(int vin) {
        for(Vehicle v: inventory) {
            if (v.getVin() == vin) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%-12s, %-15s, %-12s", name, address, phone);
    }

    public String toCSV() {
        return String.format("%s|%s|%s\n", name, address, phone);
    }
}

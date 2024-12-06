package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.models.Vehicle;

import java.util.List;

public interface VehicleDAO {
    List<Vehicle> findAllVehicles();
    List<Vehicle> findVehiclesByPriceRange(double minPrice, double maxPrice);
    List<Vehicle> findVehiclesByMakeModel(String make, String model);
    List<Vehicle> findVehiclesByYear(int year);
    List<Vehicle> findVehiclesByColor(String color);
    List<Vehicle> findVehiclesByMileage(int minMiles, int maxMiles);
    List<Vehicle> findVehiclesByVehicleType(String vehicleType);
    void addVehicleToInventory(Vehicle v);
    void removeVehicleFromInventory(Vehicle v);
    Vehicle findVehicleByVin(int vin);
}

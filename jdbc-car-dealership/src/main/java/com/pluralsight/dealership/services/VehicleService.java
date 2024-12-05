package com.pluralsight.dealership.services;

import com.pluralsight.dealership.controllers.VehicleDAO;
import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.util.List;

public class VehicleService implements VehicleDAO {
    private final DataSource dataSource;

    public VehicleService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        return List.of();
    }

    @Override
    public List<Vehicle> findVehiclesByPriceRange(double minPrice, double maxPrice) {
        return List.of();
    }

    @Override
    public List<Vehicle> findVehiclesByMakeModel(String make, String model) {
        return List.of();
    }

    @Override
    public List<Vehicle> findVehiclesByYear(int year) {
        return List.of();
    }

    @Override
    public List<Vehicle> findVehiclesByColor(String color) {
        return List.of();
    }

    @Override
    public List<Vehicle> findVehiclesByMileage(int miles) {
        return List.of();
    }

    @Override
    public List<Vehicle> findVehiclesByVehicleType(String vehicleType) {
        return List.of();
    }

    @Override
    public void addVehicleToInventory(Vehicle v) {

    }

    @Override
    public void removeVehicleFromInventory(Vehicle v) {

    }
}

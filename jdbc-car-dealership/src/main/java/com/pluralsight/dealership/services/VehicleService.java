package com.pluralsight.dealership.services;

import com.pluralsight.dealership.controllers.VehicleDAO;
import com.pluralsight.dealership.models.Dealership;
import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleService implements VehicleDAO {
    private final DataSource dataSource;

    public VehicleService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle v;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM vehicles");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int vehicleVin = rs.getInt("vin");
                int vehicleYear = rs.getInt("year");
                String vehicleMake = rs.getString("make");
                String vehicleModel = rs.getString("model");
                String vehicleType = rs.getString("vehicleType");
                String vehicleColor = rs.getString("color");
                int vehicleMiles = rs.getInt("miles");
                double vehiclePrice = rs.getDouble("price");

                v = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleMiles, vehiclePrice);

                vehicles.add(v);
            }

            return vehicles;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> findVehiclesByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle v;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM vehicles
                    WHERE price BETWEEN ? AND ?
                    """);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int vehicleVin = rs.getInt("vin");
                int vehicleYear = rs.getInt("year");
                String vehicleMake = rs.getString("make");
                String vehicleModel = rs.getString("model");
                String vehicleType = rs.getString("vehicleType");
                String vehicleColor = rs.getString("color");
                int vehicleMiles = rs.getInt("miles");
                double vehiclePrice = rs.getDouble("price");

                v = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleMiles, vehiclePrice);

                vehicles.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> findVehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle v;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM vehicles
                    WHERE make = ? AND model = ?
                    """);
            statement.setString(1, make);
            statement.setString(2, model);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int vehicleVin = rs.getInt("vin");
                int vehicleYear = rs.getInt("year");
                String vehicleMake = rs.getString("make");
                String vehicleModel = rs.getString("model");
                String vehicleType = rs.getString("vehicleType");
                String vehicleColor = rs.getString("color");
                int vehicleMiles = rs.getInt("miles");
                double vehiclePrice = rs.getDouble("price");

                v = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleMiles, vehiclePrice);

                vehicles.add(v);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
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

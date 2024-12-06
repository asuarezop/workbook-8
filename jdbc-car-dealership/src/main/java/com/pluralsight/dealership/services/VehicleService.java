package com.pluralsight.dealership.services;

import JavaHelpers.ColorCodes;
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
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle v;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM vehicles
                    WHERE year = ?
                    """);
            statement.setInt(1, year);
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
    public List<Vehicle> findVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle v;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM vehicles
                    WHERE color = ?
                    """);
            statement.setString(1, color);
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
    public List<Vehicle> findVehiclesByMileage(int minMiles, int maxMiles) {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle v;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM vehicles
                    WHERE miles BETWEEN ? AND ?
                    """);
            statement.setDouble(1, minMiles);
            statement.setDouble(2, maxMiles);
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
    public List<Vehicle> findVehiclesByVehicleType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle v;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM vehicles
                    WHERE vehicleType = ?
                    """);
            statement.setString(1, type);
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
    public void addVehicleToInventory(Vehicle v) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO vehicles(vin, year, make, model, vehicleType, color, miles, price) VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?)
                    """);
            statement.setInt(1, v.getVin());
            statement.setInt(2, v.getYear());
            statement.setString(3, v.getMake());
            statement.setString(4, v.getModel());
            statement.setString(5, v.getVehicleType());
            statement.setString(6, v.getColor());
            statement.setInt(7, v.getMiles());
            statement.setDouble(8, v.getPrice());

            //Executing and verifying INSERT query
            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Printing out vehicle to console
            UserInterface.printDealershipHeader();
            System.out.println(v);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Vehicle was added to inventory!" + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeVehicleFromInventory(Vehicle v) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    DELETE FROM vehicles WHERE vin = ?
                    """);
            statement.setInt(1, v.getVin());

            //Executing and verifying DELETE query
            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Vehicle removed from dealership." + ColorCodes.RESET);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

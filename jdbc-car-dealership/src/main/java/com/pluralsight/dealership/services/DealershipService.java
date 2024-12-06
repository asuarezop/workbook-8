package com.pluralsight.dealership.services;

import com.pluralsight.dealership.controllers.DealershipDAO;
import com.pluralsight.dealership.models.Dealership;
import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipService implements DealershipDAO {
    private final DataSource dataSource;

    public DealershipService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    //Retrieving all dealerships from database
    public List<Dealership> findAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();

        Dealership d;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM dealerships");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int dealershipId = rs.getInt("id");
                String dealershipName = rs.getString("name");
                String dealershipAddress = rs.getString("address");
                String dealershipPhoneNum = rs.getString("phone");

                d = new Dealership(dealershipId, dealershipName, dealershipAddress, dealershipPhoneNum);

                dealerships.add(d);
            }

            return dealerships;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveDealership(Dealership d) throws IOException {
        //Inserting a new dealership into database
//        try {
//
//        } catch (IOException e) {
//            throw new IOException(e);
//        }
    }

    @Override
    public Dealership findDealershipById(int id) {
        Dealership d;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM dealerships WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int dealershipId = rs.getInt("id");
                String dealershipName = rs.getString("name");
                String dealershipAddress = rs.getString("address");
                String dealershipPhoneNum = rs.getString("phone");

                d = new Dealership(dealershipId, dealershipName, dealershipAddress, dealershipPhoneNum);

                return d;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}


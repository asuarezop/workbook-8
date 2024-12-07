package com.pluralsight.dealership.services;

import JavaHelpers.ColorCodes;
import com.pluralsight.dealership.controllers.DealershipDAO;
import com.pluralsight.dealership.models.Dealership;

import javax.sql.DataSource;
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
                d = createDealershipObj(rs);
                dealerships.add(d);
            }

            return dealerships;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveDealership(Dealership d) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO dealerships(id, name, address, phone) VALUES
                    (?, ?, ?, ?)
                    """);
            statement.setInt(1, d.getId());
            statement.setString(2, d.getName());
            statement.setString(3, d.getAddress());
            statement.setString(4, d.getPhone());

            //Executing and verifying INSERT query
            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Printing out dealership to console
            UserInterface.printDealershipHeader();
            System.out.println(d);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Dealership was added to database!" + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeDealership(Dealership d) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    DELETE FROM dealerships WHERE id = ?
                    """);
            statement.setInt(1, d.getId());

            //Executing and verifying DELETE query
            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Dealership removed from database." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Dealership findDealershipById(int id) {
        Dealership d;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM dealerships WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                d = createDealershipObj(rs);
                return d;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private Dealership createDealershipObj(ResultSet rs) throws SQLException {
        int dealershipId = rs.getInt("id");
        String dealershipName = rs.getString("name");
        String dealershipAddress = rs.getString("address");
        String dealershipPhoneNum = rs.getString("phone");

        return new Dealership(dealershipId, dealershipName, dealershipAddress, dealershipPhoneNum);
    }
}


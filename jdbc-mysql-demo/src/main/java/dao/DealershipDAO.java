package dao;

import models.Dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DealershipDAO {
    private final DataSource dataSource;

    public DealershipDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //Retrieving a dealership based on id
    public Dealership findDealershipById(int id) {
        String dealershipName = "";
        String dealershipPhone = "";
        String dealershipAddress = "";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM dealerships WHERE dealership_id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                dealershipName = rs.getString("name");
                dealershipPhone = rs.getString("phone");
                dealershipAddress = rs.getString("address");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Dealership(id, dealershipName, dealershipPhone, dealershipAddress);
    }
}

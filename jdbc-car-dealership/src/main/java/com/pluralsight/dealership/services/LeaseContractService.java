package com.pluralsight.dealership.services;

import com.pluralsight.dealership.controllers.LeaseDAO;
import com.pluralsight.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LeaseContractService implements LeaseDAO {
    private final DataSource dataSource;

    public LeaseContractService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<LeaseContract> findAllLeaseContracts() {
        return List.of();
    }

    @Override
    public LeaseContract findLeaseContractById(int id) {
        return null;
    }

    @Override
    public void saveLeaseContract(LeaseContract c) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO lease_contracts(vin, lease_date, customer_name, customer_email, expected_end_value, lease_fee, down_payment, monthly_payment) VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?)
                    """);
            statement.setInt(1, c.getVehicleSold().getVin());
            statement.setDate(2, Date.valueOf(c.getDate()));
            statement.setString(3, c.getCustomerName());
            statement.setString(4, c.getCustomerEmail());
            statement.setDouble(5, c.getExpectedEndValue());
            statement.setDouble(6, c.getLeaseFee());
            statement.setDouble(7, c.getDownPayment());
            statement.setDouble(8, c.getMonthlyPayment());

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteLeaseContract(LeaseContract c) {

    }
}

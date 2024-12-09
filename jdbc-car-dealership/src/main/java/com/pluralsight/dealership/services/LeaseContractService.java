package com.pluralsight.dealership.services;

import JavaHelpers.ColorCodes;
import com.pluralsight.dealership.controllers.LeaseDAO;
import com.pluralsight.dealership.models.LeaseContract;
import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractService implements LeaseDAO {
    private final DataSource dataSource;

    public LeaseContractService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<LeaseContract> findAllLeaseContracts() {
        List<LeaseContract> leases = new ArrayList<>();
        LeaseContract lc;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM lease_contracts
                    """);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                lc = createLeaseContractObj(rs);;
                leases.add(lc);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return leases;
    }

    @Override
    public List<LeaseContract> findLeaseContractById(int id) {
        List<LeaseContract> lease = new ArrayList<>();
        LeaseContract lc;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM lease_contracts
                    WHERE id = ?
                    """);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                lc = createLeaseContractObj(rs);
                lease.add(lc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lease;
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
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    DELETE FROM lease_contracts WHERE id = ?
                    """);
            statement.setInt(1, c.getId());

            //Executing and verifying DELETE query
            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Lease contract removed from database." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private LeaseContract createLeaseContractObj(ResultSet rs) throws SQLException {
        Vehicle v;
        LocalDate leaseDate = rs.getDate("lease_date").toLocalDate();
        String customerName = rs.getString("customer_name");
        String customerEmail = rs.getString("customer_email");
        int vehicleVin = rs.getInt("vin");

        v = UserInterface.vehicleManager.findVehicleByVin(vehicleVin);

        return new LeaseContract(leaseDate, customerName, customerEmail, v);
    }
}

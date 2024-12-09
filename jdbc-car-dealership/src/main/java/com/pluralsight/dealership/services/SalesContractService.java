package com.pluralsight.dealership.services;

import JavaHelpers.ColorCodes;
import com.pluralsight.dealership.controllers.SalesDAO;
import com.pluralsight.dealership.models.LeaseContract;
import com.pluralsight.dealership.models.SalesContract;
import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesContractService implements SalesDAO {
    private final DataSource dataSource;

    public SalesContractService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SalesContract> findAllSalesContracts() {
        List<SalesContract> sales = new ArrayList<>();
        SalesContract sc;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM sales_contracts
                    """);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                sc = createSalesContractObj(rs);
                sales.add(sc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sales;
    }

    @Override
    public List<SalesContract> findSalesContractById(int id) {
        List<SalesContract> sale = new ArrayList<>();
        SalesContract sc;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM sales_contracts
                    WHERE id = ?
                    """);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                sc = createSalesContractObj(rs);
                sale.add(sc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sale;
    }

    @Override
    public void saveSalesContract(SalesContract c) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO sales_contracts(vin, sale_date, customer_name, customer_email, sales_tax, recording_fee, processing_fee, down_payment, monthly_payment, financed) VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """);
            statement.setInt(1, c.getVehicleSold().getVin());
            statement.setDate(2, Date.valueOf(c.getDate()));
            statement.setString(3, c.getCustomerName());
            statement.setString(4, c.getCustomerEmail());
            statement.setDouble(5, c.getSalesTax());
            statement.setDouble(6, c.getRecordingFee());
            statement.setDouble(7, c.getProcessingFee());
            statement.setDouble(8, c.getDownPayment());
            statement.setDouble(9, c.getMonthlyPayment());
            statement.setBoolean(10, c.isFinanced());

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSalesContract(SalesContract c) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    DELETE FROM sales_contracts WHERE id = ?
                    """);
            statement.setInt(1, c.getId());

            //Executing and verifying DELETE query
            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Sales contract removed from database." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SalesContract createSalesContractObj(ResultSet rs) throws SQLException {
        Vehicle v;
        LocalDate saleDate = rs.getDate("sale_date").toLocalDate();
        String customerName = rs.getString("customer_name");
        String customerEmail = rs.getString("customer_email");
        int vehicleVin = rs.getInt("vin");

        v = UserInterface.vehicleManager.findVehicleByVin(vehicleVin);

        return new SalesContract(saleDate, customerName, customerEmail, v);
    }
}

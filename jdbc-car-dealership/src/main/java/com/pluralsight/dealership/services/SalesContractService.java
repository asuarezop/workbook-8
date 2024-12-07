package com.pluralsight.dealership.services;

import com.pluralsight.dealership.controllers.SalesDAO;
import com.pluralsight.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SalesContractService implements SalesDAO {
    private final DataSource dataSource;

    public SalesContractService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SalesContract> findAllSalesContracts() {
        return List.of();
    }

    @Override
    public SalesContract findSalesContractById(int id) {
        return null;
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

    }
}

package com.pluralsight.dealership.services;

import com.pluralsight.dealership.controllers.LeaseDAO;
import com.pluralsight.dealership.models.LeaseContract;

import javax.sql.DataSource;
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

    }

    @Override
    public void deleteLeaseContract(LeaseContract c) {

    }
}

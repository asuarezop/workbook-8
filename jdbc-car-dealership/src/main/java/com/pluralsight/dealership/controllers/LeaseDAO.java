package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.models.LeaseContract;

import java.util.List;

public interface LeaseDAO {
    List<LeaseContract> findAllLeaseContracts();
    LeaseContract findLeaseContractById(int id);
    void saveLeaseContract(LeaseContract c);
    void deleteLeaseContract(LeaseContract c);
}

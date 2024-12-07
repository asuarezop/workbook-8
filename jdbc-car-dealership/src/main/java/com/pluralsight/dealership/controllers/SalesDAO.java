package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.models.SalesContract;

import java.util.List;

public interface SalesDAO {
    List<SalesContract> findAllSalesContracts();
    SalesContract findSalesContractById(int id);
    void saveSalesContract(SalesContract c);
    void deleteSalesContract(SalesContract c);
}

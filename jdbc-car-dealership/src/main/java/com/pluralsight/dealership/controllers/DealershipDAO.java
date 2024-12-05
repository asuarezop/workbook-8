package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.models.Dealership;

import java.util.List;

public interface DealershipDAO {
    Dealership findDealershipById(int id);
    List<Dealership> findAllDealerships();
}

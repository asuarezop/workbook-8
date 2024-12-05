package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.models.Dealership;

public interface DealershipDAO {
    Dealership findDealershipById(int id);
}

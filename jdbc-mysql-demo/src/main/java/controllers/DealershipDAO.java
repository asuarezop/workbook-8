package controllers;


import models.Dealership;

public interface DealershipDAO {
    Dealership findDealershipById(int id);
}

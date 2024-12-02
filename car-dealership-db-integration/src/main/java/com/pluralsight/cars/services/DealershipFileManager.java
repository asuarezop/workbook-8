package com.pluralsight.cars.services;

import com.pluralsight.cars.models.Dealership;
import com.pluralsight.cars.models.Vehicle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class DealershipFileManager {
    //String variable to hold inventory CSV file path
    private static final String inventoryCSV = "src/main/resources/inventory.csv";

    //Retrieving a Dealership object, load and read from inventory.csv
    public static Dealership getDealership() {
        Dealership d = new Dealership();
        Vehicle v;

        try {
            //Calling openFileReader method to initialize BufferedReader
            BufferedReader bufReader = FileHandler.openFileReader(inventoryCSV);

            //Reading each line of input from fileContents
            String fileContents;

            while ((fileContents = bufReader.readLine()) != null) {
                String[] dealershipData = fileContents.split("\\|");

                if (fileContents.startsWith("D")) {
                    //Constructing a new Dealership object and passing in header data from inventory.csv
                    String dealershipName = dealershipData[0];
                    String dealershipAddress = dealershipData[1];
                    String dealershipPhoneNum = dealershipData[2];
                    d = new Dealership(dealershipName, dealershipAddress, dealershipPhoneNum);
                    continue;
                }

                //Passing in vehicle data into new ArrayList of vehicles
                int vin = Integer.parseInt(dealershipData[0]);
                int year = Integer.parseInt(dealershipData[1]);
                String make = dealershipData[2];
                String model = dealershipData[3];
                String type = dealershipData[4];
                String color = dealershipData[5];
                int miles = Integer.parseInt(dealershipData[6]);
                double price = Double.parseDouble(dealershipData[7]);

                //Creating a new vehicle object, passing vehicle variables to constructor
                v = new Vehicle(vin, year, make, model, type, color, miles, price);

                //Adding each vehicle to inventory ArrayList
                d.getInventory().add(v);
//                d.getInventory().sort((v1, v2) -> v2.getVin().compareTo));
            }
            bufReader.close();

            //Returning back Dealership object
            return d;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveDealership(Dealership d) throws IOException {
        try {
            BufferedWriter bufWriter = FileHandler.getBufferedWriter(inventoryCSV);

            //Writing dealership header to csv file
            bufWriter.write(d.toCSV());

            //Writing all vehicles from dealership inventory to csv file
            for (Vehicle v: d.getInventory()) {
               bufWriter.write(v.toCSV());
            }
            bufWriter.close();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}


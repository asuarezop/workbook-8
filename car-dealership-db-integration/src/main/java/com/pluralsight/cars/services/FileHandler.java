package com.pluralsight.cars.services;

import java.io.*;

public class FileHandler {
    //Initializing the BufferedWriter
    public static BufferedWriter getBufferedWriter(String filename) throws IOException {
        BufferedWriter bufWriter = null;
        if (filename.contains("inventory.csv")) {
            //Overwriting entire inventory csv file each time a vehicle is added/removed
            bufWriter = new BufferedWriter(new FileWriter(filename));
        } else if (filename.contains("contracts.csv")) {
            //Activate append mode to add new contracts to end of contracts csv file
            bufWriter = new BufferedWriter(new FileWriter(filename, true));
        }
        return bufWriter;
    }

    //Initializing the BufferedReader
    public static BufferedReader openFileReader(String filename) throws FileNotFoundException {
        BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        return bufReader;
    }
}

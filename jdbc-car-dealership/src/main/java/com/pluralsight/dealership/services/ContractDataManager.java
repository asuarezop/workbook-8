package com.pluralsight.dealership.services;

import com.pluralsight.dealership.models.Contract;

import java.io.BufferedWriter;
import java.io.IOException;

public class ContractDataManager {
    //String variable to hold contract CSV file path
    private static final String contractCSV = "src/main/resources/contracts.csv";

    //Not needed unless doing bonus
//    public static void readContractFile() {
//        SalesContract s;
//        try {
//            BufferedReader bufReader = FileHandler.openFileReader(contractCSV);
//
//            //Reading each line of input from fileContents
//            String fileContents;
//
//            while ((fileContents = bufReader.readLine()) != null) {
//                String[] contractData = fileContents.split("\\|");
//
//                if (fileContents.startsWith("S")) {
//                    String contractType = contractData[0];
//                    String contractDate = contractData[1];
//                    String customerName = contractData[2];
//                    String customerEmail = contractData[3];
//                    s = new SalesContract(contractDate, customerName, customerEmail);
//                    continue;
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void saveContract(Contract c) {
        try {
            BufferedWriter bufWriter = FileHandler.getBufferedWriter(contractCSV);

            bufWriter.write(c.toString());

            bufWriter.close();
        } catch (IOException e) {
//            throw new IOException(e);
        }
    }
}

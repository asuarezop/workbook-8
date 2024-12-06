package com.pluralsight.dealership.services;

import JavaHelpers.ColorCodes;
import com.pluralsight.dealership.models.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class UserInterface {
    //Config path to retrieve properties from database
    private final String configFilePath = "src/main/resources/config.properties";

    //Instance variable for DealershipService object
    private DealershipService dealershipManager;

    //Instance variable for VehicleService object
    private VehicleService vehicleManager;

    //Holds a Properties object for grabbing database credentials
    private Properties properties;

    //Related to input from user
    static String userInput;

    //Initializing scanner to read from terminal input
    static Scanner inputSc = new Scanner(System.in);

    //Dealership selected by user based on id
    static int dealershipChoice;

    //Boolean condition to exit application screens
    static boolean exitApp = false;

    //init(): This method gets called first before any other methods are run inside main()
    private void init() {
        properties = new Properties();

        try {
            BasicDataSource dataSource = new BasicDataSource();
            BufferedReader bufReader = new BufferedReader(new FileReader(configFilePath));
            properties.load(bufReader);

            //Establishing database connection using credentials from .properties file
            String url = properties.getProperty("DB_URL");
            String user = properties.getProperty("DB_USER");
            String password = properties.getProperty("DB_PASSWORD");

            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);

            DealershipService ds = new DealershipService(dataSource);
            VehicleService vs = new VehicleService(dataSource);

            //Initializing dealership and vehicle service classes
            this.dealershipManager = ds;
            this.vehicleManager = vs;



        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void showHomeScreen() throws IOException {
        String homeScreenMenuHeader = """
                =================================
                |      DEALERSHIP APP (HOME)    |
                =================================
                """;
        String prompt = """
                \nPlease select what type of request to filter from dealership inventory:
                
                [1] Price - filter vehicles within a price range
                [2] Make Model - filter vehicles by make/model
                [3] Year - filter vehicles by year range
                [4] Color - filter vehicles by color
                [5] Mileage - filter vehicles by mileage range
                [6] Vehicle Type - filter vehicles by type (SUV, Sedan, Hatchback, etc.)
                [7] All Vehicles - display every vehicle from inventory
                [8] Add Vehicle - adds a new vehicle to inventory
                [9] Remove Vehicle - removes a vehicle from inventory
                [10] Sell/Lease Vehicle - select vehicle to put up for sale/lease in a contract
                [X] Exit Application - quits running application
                """;

        do {
            init();
            System.out.println(homeScreenMenuHeader);

            //Grabbing selected dealership from user
            Dealership d = promptDealership();

            System.out.println(prompt);
            userInput = inputSc.nextLine().trim().toUpperCase();

            switch (userInput) {
                case "1":
                    processGetByPriceRequest(d);
                    break;
                case "2":
                    processGetByMakeModelRequest(d);
                    break;
                case "3":
                    processGetByYearRequest(d);
                    break;
                case "4":
                    processGetByColorRequest(d);
                    break;
                case "5":
                    processGetByMileageRequest(d);
                    break;
                case "6":
                    processGetByVehicleTypeRequest(d);
                    break;
                case "7":
                    processGetAllVehiclesRequest(d);
                    break;
                case "8":
                    processAddVehicleRequest(d);
                    break;
                case "9":
                    processRemoveVehicleRequest(d);
                    break;
                case "10":
                    processSellLeaseVehicleRequest(d);
                    break;
                case "X":
                    exitApp = true;
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!exitApp);
    }

    //Other non-static methods to process user requests
    public void processGetByPriceRequest(Dealership dealership) {
        promptInstructions("Enter your desired price range to search vehicles from:  " + dealership.getName());

        String min = promptUser("Minimum value: ");
        double minPrice = Double.parseDouble(min);

        String max = promptUser("Maximum value: ");
        double maxPrice = Double.parseDouble(max);

        List<Vehicle> vehicles = vehicleManager.findVehiclesByPriceRange(minPrice, maxPrice);
        printVehicleList(vehicles);
    }

    public void processGetByMakeModelRequest(Dealership dealership) {
        promptInstructions("Enter vehicle make and model to search vehicles from:  " + dealership.getName());
        String vehicleMake = promptUser("Make: ");
        String vehicleModel = promptUser("Model: ");

        if (!vehicleMake.isEmpty() && !vehicleModel.isEmpty()) {
            List<Vehicle> vehicles = vehicleManager.findVehiclesByMakeModel(vehicleMake, vehicleModel);
            printVehicleList(vehicles);
        } else {
            System.out.println("No vehicles matched your provided make/model. Please try again.");
        }
    }

    public void processGetByYearRequest(Dealership dealership) {
        promptInstructions("Enter vehicle year to search vehicles from:  " + dealership.getName());
        String vehicleYear = promptUser("Year: ");
        int year = Integer.parseInt(vehicleYear);

        String parsedYear = String.valueOf(year);

        //Checking length of String parsedYear is not greater than 4
        if (year != 0 && parsedYear.length() == 4) {
            List<Vehicle> vehicles = vehicleManager.findVehiclesByYear(year);
            printVehicleList(vehicles);
        } else {
            System.out.println("No vehicles matched given year. Please try again.");
        }
    }

    public void processGetByColorRequest(Dealership dealership) {
        promptInstructions("Enter vehicle color to search vehicles from:  " + dealership.getName());
        String vehicleColor = promptUser("Color: ");

        if (!vehicleColor.isEmpty()) {
            List<Vehicle> vehicles = vehicleManager.findVehiclesByColor(vehicleColor);
            printVehicleList(vehicles);
        } else {
            System.out.println("No vehicles found that match given color. Please try again.");
        }
    }

    public void processGetByMileageRequest(Dealership dealership) {
        promptInstructions("Enter your desired mileage range to search vehicles from:  " + dealership.getName());
        String min = promptUser("Minimum mileage: ");
        int minMileage = Integer.parseInt(min);

        String max = promptUser("Maximum mileage: ");
        int maxMileage = Integer.parseInt(max);

        if (minMileage != 0 && maxMileage != 0) {
            List<Vehicle> vehicles = vehicleManager.findVehiclesByMileage(minMileage, maxMileage);
            printVehicleList(vehicles);
        } else {
            System.out.println("No vehicles found that match provided mileage range. Please try again.");
        }
    }

    public void processGetByVehicleTypeRequest(Dealership dealership) {
        promptInstructions("Enter vehicle type to search vehicles from:  " + dealership.getName());
        String vehicleType = promptUser("Type: ");

        if (!vehicleType.isEmpty()) {
            List<Vehicle> vehicles = vehicleManager.findVehiclesByVehicleType(vehicleType);
            printVehicleList(vehicles);
        } else {
            System.out.println("Invalid vehicle type. Please try again.");
        }
    }

    public void processGetAllVehiclesRequest(Dealership dealership) {
        promptInstructions("Inventory for:  " + dealership.getName());

        List<Vehicle> vehicles = vehicleManager.findAllVehicles();
        printVehicleList(vehicles);
    }

    public void processAddVehicleRequest(Dealership dealership) {
        Vehicle v;
        promptInstructions("Enter new vehicle to add into:  " + dealership.getName());

        String usedVehicleVIN = promptUser("VIN: ");
        int parsedUsedVehicleVIN = Integer.parseInt(usedVehicleVIN);

        String usedVehicleYear = promptUser("Year: ");
        int parsedUsedVehicleYear = Integer.parseInt(usedVehicleYear);

        String usedVehicleMake = promptUser("Make: ");
        String usedVehicleModel = promptUser("Model: ");
        String usedVehicleType = promptUser("Type: ");
        String usedVehicleColor = promptUser("Color: ");

        String usedVehicleMileage = promptUser("Mileage: ");
        int parsedUsedVehicleMileage = Integer.parseInt(usedVehicleMileage);

        String usedVehiclePrice = promptUser("Price: ");
        double parsedUsedVehiclePrice = Double.parseDouble(usedVehiclePrice);

        v = new Vehicle(parsedUsedVehicleVIN, parsedUsedVehicleYear, usedVehicleMake, usedVehicleModel, usedVehicleType, usedVehicleColor, parsedUsedVehicleMileage, parsedUsedVehiclePrice);

        vehicleManager.addVehicleToInventory(v);
    }

    public void processRemoveVehicleRequest(Dealership dealership) {
        Vehicle v;
        promptInstructions("Enter desired vehicle you wish to remove from:  " + dealership.getName());
        String vehicleVin = promptUser("VIN: ");
        int parsedVehicleVin = Integer.parseInt(vehicleVin);

        //Creating a vehicle with only VIN for comparison with removeVehicle()
        v = new Vehicle(parsedVehicleVin);

        vehicleManager.removeVehicleFromInventory(v);
    }

    public void processSellLeaseVehicleRequest(Dealership dealership) {
        Vehicle v;
        promptInstructions("Would you like to sell or lease vehicle?:  ");
        String contractOption = promptUser("""
                [1] Sell
                [2] Lease
                """);
        int parsedContractOption = Integer.parseInt(contractOption);
        promptInstructions("Enter the VIN of the vehicle to put in sale/lease contract from:  " + dealership.getName());
        String selectedVehicle = promptUser("VIN: ");
        int parsedSelectedVehicle = Integer.parseInt(selectedVehicle);

        v = vehicleManager.findVehicleByVin(parsedSelectedVehicle);

        //Removing vehicle from dealership inventory
        vehicleManager.removeVehicleFromInventory(v);

        if (parsedContractOption == 1) {
            promptContractDetails("sale" , v);
        } else if (parsedContractOption == 2) {
            promptContractDetails("lease", v);
        }
    }

    //Retrieves dealerships from database
    public Dealership promptDealership() {
        //Querying for all dealerships
        List<Dealership> dealerships = dealershipManager.findAllDealerships();

        for (Dealership d: dealerships) {
            System.out.printf("%d) %s, %s, %s \n", d.getId(), d.getName(), d.getAddress(), d.getPhone());
        }

        //Prompting for user dealership choice
        System.out.println("\nEnter dealership to search from: ");
        dealershipChoice = inputSc.nextInt();
        inputSc.nextLine();

        return dealershipManager.findDealershipById(dealershipChoice);
    }

    //Retrieves user input from a prompt
    public String promptUser(String prompt) {
        System.out.print(ColorCodes.WHITE + prompt + ColorCodes.RESET);
        return userInput = inputSc.nextLine().trim();
    }

    public void promptInstructions(String prompt) {
        String[] textDetails = prompt.split(": ");
        System.out.println(ColorCodes.LIGHT_BLUE + textDetails[0] + ColorCodes.ORANGE_BOLD + ColorCodes.ITALIC + textDetails[1] + ColorCodes.RESET);
    }

    public void promptContractDetails(String contractType, Vehicle vehicle) {
        SalesContract vehicleSale;
        LeaseContract vehicleLease;
        String[] contractPrompts = {"Enter the date associated with the " + contractType + ":  ", "Enter the customer name associated with the " + contractType + ":  ", "Enter the customer email associated with the " + contractType + ":  ", "Enter whether the vehicle was financed or not:  ", "How much would you like to put towards down payment?:  "};
        String[] userInputPrompts = {"Date: ", "Customer name: ", "Customer email: ", """
                    [1] Yes
                    [2] No
                    """, "Down payment amount: "};

        //Prompting for date
        promptInstructions(contractPrompts[0]);
        String dateOfContract = promptUser(userInputPrompts[0]);

        //Prompting for customer name
        promptInstructions(contractPrompts[1]);
        String customerName = promptUser(userInputPrompts[1]);

        //Prompting for customer email
        promptInstructions(contractPrompts[2]);
        String customerEmail = promptUser(userInputPrompts[2]);

        if (contractType.equals("sale")) {

            //Prompting to determine if vehicle was financed
            promptInstructions(contractPrompts[3]);
            String financedOption = promptUser(userInputPrompts[3]);

            //Passing in sales data from the user into new SalesContract object
            vehicleSale = new SalesContract(dateOfContract, customerName, customerEmail, vehicle);

            if (financedOption.equals("1")) {
                //Setting isFinanced boolean variable to true in SalesContract
                vehicleSale.setFinanced(true);

                //Prompting to determine down payment for vehicle
                promptInstructions(contractPrompts[4]);
                double downPayment = Double.parseDouble(promptUser(userInputPrompts[4]));

                //Setting down payment variable to user's amount for SalesContract
                vehicleSale.setDownPayment(downPayment);

            } else if (financedOption.equals("2")) {
                //Setting isFinanced boolean variable to false in SalesContract
                vehicleSale.setFinanced(false);

                //Indicating user intends to pay with cash
                vehicleSale.setDownPayment(0);
            }

            //Saving SalesContract data to contacts.csv file
            ContractDataManager.saveContract(vehicleSale);

        } else if (contractType.equals("lease")) {
            //Passing in lease data from the user into new LeaseContract object
            vehicleLease = new LeaseContract(dateOfContract, customerName, customerEmail, vehicle);

            //Prompting to determine down payment for vehicle
            promptInstructions(contractPrompts[4]);
            double downPayment = Double.parseDouble(promptUser(userInputPrompts[4]));

            //Setting down payment variable to user's amount for LeaseContract
            vehicleLease.setDownPayment(downPayment);

            //Saving LeaseContract data to contacts.csv file
            ContractDataManager.saveContract(vehicleLease);
        }

        //Confirmation message
        System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Contract has been saved." + ColorCodes.RESET);
    }

    public static void printDealershipHeader() {
        String dealershipHeader = ColorCodes.LIGHT_BLUE_UNDERLINED + String.format("%-10s %-8s %-15s %-13s %-17s %-10s %-12s %-12s", "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price") + ColorCodes.RESET;
        System.out.println(dealershipHeader);
    }

    private static void printVehicleList(List<Vehicle> vehicles) {
        if (!vehicles.isEmpty()) {
            printDealershipHeader();
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        } else {
            System.out.println("No vehicles matched your input.");
        }
    }
}

package com.pluralsight.dealership.services;

import com.pluralsight.dealership.models.Dealership;
import com.pluralsight.dealership.models.Vehicle;


public class AdminUserInterface {
    public void showAdminScreen(Dealership dealership) {
        String adminScreenMenuHeader = """
                ================================
                |     DEALERSHIP APP (ADMIN)   |
                ================================
                """;
        String prompt = """
                \nPlease select what type of request to search from database:
                
                [1] Update Vehicle - updates vehicle sale status from contract
                [2] Add Dealership - adds a new dealership
                [3] Remove Dealership - removes a dealership
                [4] All Contracts - display every sales/lease contract
                [X] Exit Application - quits running application
                """;

        do {
            System.out.println(adminScreenMenuHeader);
            System.out.println(prompt);

            UserInterface.userInput = UserInterface.inputSc.nextLine().trim().toUpperCase();

            switch (UserInterface.userInput) {
                case "1":
                    processUpdateVehicleInvRequest(dealership);
                    break;
                case "2":
//                    processAddDealershipRequest(dealership);
                    break;
                case "3":
//                    processRemoveDealershipRequest(dealership);
                    break;
                case "X":
                    UserInterface.exitApp = true;
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!UserInterface.exitApp);
    }

    public void processUpdateVehicleInvRequest(Dealership dealership) {
        Vehicle v;
        UserInterface.promptInstructions("Enter the VIN of the vehicle to update from:  " + dealership.getName());
        String selectedVehicle = UserInterface.promptUser("VIN: ");
        int parsedSelectedVehicle = Integer.parseInt(selectedVehicle);

        v = UserInterface.vehicleManager.findVehicleByVin(parsedSelectedVehicle);

        UserInterface.promptInstructions("Confirm whether vehicle was sold:  ");
        boolean vehicleStatus = UserInterface.promptVehicleSold("""
                [1] Yes
                [2] No
                """);

        //Updating vehicle sold status for selected vehicle
        UserInterface.vehicleManager.updateVehicleFromInventory(vehicleStatus, v);
    }
}

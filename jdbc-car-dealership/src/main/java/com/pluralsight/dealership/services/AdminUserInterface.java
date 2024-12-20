package com.pluralsight.dealership.services;

import JavaHelpers.ColorCodes;
import com.pluralsight.dealership.models.Dealership;
import com.pluralsight.dealership.models.LeaseContract;
import com.pluralsight.dealership.models.SalesContract;
import com.pluralsight.dealership.models.Vehicle;

import java.util.List;


public class AdminUserInterface {
    public void showAdminScreen(Dealership dealership) {
        String adminScreenMenuHeader = ColorCodes.CYAN + """
                ================================
                |     DEALERSHIP APP (ADMIN)   |
                ================================
                """ + ColorCodes.RESET;
        String prompt = """
                \nPlease select what type of request to search from database:
                
                [1] Update Vehicle - updates vehicle sale status from contract
                [2] View All Dealerships - display every dealership
                [3] Add Dealership - adds a new dealership
                [4] Remove Dealership - removes a dealership
                [5] All Sales Contracts - display every sales contract
                [6] Remove Sales Contract - removes a specific sale contract
                [7] Sales Contract By ID - filter for sale contracts by id
                [8] All Lease Contracts - display every lease contract
                [9] Remove Lease Contract - removes a specific lease contract
                [10] Lease Contract By ID - filter for lease contracts by id
                [X] Exit Admin Panel - quits running application in admin mode
                """;
        boolean exitAdmin = false;

        do {
            System.out.println(adminScreenMenuHeader);
            System.out.println(prompt);

            UserInterface.userInput = UserInterface.inputSc.nextLine().trim().toUpperCase();

            switch (UserInterface.userInput) {
                case "1":
                    processUpdateVehicleInvRequest(dealership);
                    break;
                case "2":
                    processGetAllDealershipsRequest();
                    break;
                case "3":
                    processAddDealershipRequest();
                    break;
                case "4":
                    processRemoveDealershipRequest();
                    break;
                case "5":
                    processGetAllSalesContracts();
                    break;
                case "6":
                    processDeleteSalesContract();
                    break;
                case "7":
                    processGetSalesContractById();
                    break;
                case "8":
                    processGetAllLeaseContracts();
                    break;
                case "9":
                    processDeleteLeaseSalesContract();
                    break;
                case "10":
                    processGetLeaseContractById();
                    break;
                case "X":
                    exitAdmin = true;
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option. Please make your selection.");
            }
        } while (!exitAdmin);
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

    public void processGetAllDealershipsRequest() {
        List<Dealership> dealerships = UserInterface.dealershipManager.findAllDealerships();

        UserInterface.printDealershipList(dealerships);
    }

    public void processAddDealershipRequest() {
        Dealership d;

        UserInterface.promptInstructions("Enter new dealership to add into:  ");
        String dealershipId = UserInterface.promptUser("ID: ");
        int parsedDealershipId = Integer.parseInt(dealershipId);

        String dealershipName = UserInterface.promptUser("Dealership Name: ");
        String dealershipAddress = UserInterface.promptUser("Dealership Address: ");
        String dealershipPhone = UserInterface.promptUser("Dealership Phone: ");

        d = new Dealership(parsedDealershipId, dealershipName, dealershipAddress, dealershipPhone);

        UserInterface.dealershipManager.saveDealership(d);
    }

    public void processRemoveDealershipRequest() {
        Dealership d;

        UserInterface.promptInstructions("Enter the dealership you wish to remove:  ");
        String dealershipId = UserInterface.promptUser("Dealership ID: ");
        int parsedDealershipId = Integer.parseInt(dealershipId);

        d = new Dealership(parsedDealershipId);

        UserInterface.dealershipManager.removeDealership(d);
    }

    public void processGetAllSalesContracts() {
        List<SalesContract> sales = UserInterface.salesManager.findAllSalesContracts();

        UserInterface.printContractList(sales);
    }

    public void processGetAllLeaseContracts() {
        List<LeaseContract> leases = UserInterface.leaseManager.findAllLeaseContracts();

        UserInterface.printContractList(leases);
    }

    public void processDeleteSalesContract() {
        SalesContract sc;
        UserInterface.promptInstructions("Enter the sales contract you wish to remove:  ");
        String salesId =  UserInterface.promptUser("Sales Contract ID: ");
        int parsedSalesId = Integer.parseInt(salesId);

        //Find the vehicle vin associated with the given sales contract ID
        int vehicleVin = UserInterface.vehicleManager.findVehicleVinByContractId(parsedSalesId);

        sc = new SalesContract(parsedSalesId, vehicleVin);

        UserInterface.salesManager.deleteSalesContract(sc);
    }

    public void processDeleteLeaseSalesContract() {
        LeaseContract lc;
        UserInterface.promptInstructions("Enter the lease contract you wish to remove:  ");
        String leaseId =  UserInterface.promptUser("Lease Contract ID: ");
        int parsedLeaseId = Integer.parseInt(leaseId);

        //Find the vehicle vin associated with the given lease contract ID
        int vehicleVin = UserInterface.vehicleManager.findVehicleVinByContractId(parsedLeaseId);

        lc = new LeaseContract(parsedLeaseId, vehicleVin);

        UserInterface.leaseManager.deleteLeaseContract(lc);
    }

    public void processGetSalesContractById() {
        UserInterface.promptInstructions("Enter the sales contract ID to find matching sales contract:  ");
        String salesId = UserInterface.promptUser("Sales Contract ID: ");
        int parsedSalesId = Integer.parseInt(salesId);

       List<SalesContract> sale = UserInterface.salesManager.findSalesContractById(parsedSalesId);
       UserInterface.printContractList(sale);
    }

    public void processGetLeaseContractById() {
        UserInterface.promptInstructions("Enter the lease contract ID to find matching lease contract:  ");
        String leaseId = UserInterface.promptUser("Lease Contract ID: ");
        int parsedLeaseId = Integer.parseInt(leaseId);

        List<LeaseContract> lease = UserInterface.leaseManager.findLeaseContractById(parsedLeaseId);
        UserInterface.printContractList(lease);
    }
}

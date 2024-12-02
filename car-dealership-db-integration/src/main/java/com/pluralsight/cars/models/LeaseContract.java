package com.pluralsight.cars.models;

public class LeaseContract extends Contract {
    private final double expectedEndValue = 0.50;
    private final double leaseFee = 0.07;
    private double downPayment;
    private double monthlyPayment;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    public double getExpectedEndValue() {
        return expectedEndValue * getVehicleSold().getPrice();
    }

    public double getLeaseFee() {
        return leaseFee * getVehicleSold().getPrice();
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    @Override
    public double getTotalPrice() {
        return getExpectedEndValue() + getLeaseFee() + 100.00;
    }

    @Override
    public double getMonthlyPayment() {
        int leaseTerm = 36;
        //Money factor formula = 4/2400  --> Interest rate of 4% / 2400
        double moneyFactor = (double) 4 / 2400;
        //Adjusted Capitalized Cost formula = Original price of vehicle - down payment
        double adjustedCapitalizedCost = getVehicleSold().getPrice() - getDownPayment();
        //Estimated value of an asset at the end of the lease term
        double residualValue = getExpectedEndValue();
        //The loss of value in asset (vehicle) over the lease term
        double depreciatingCost = (adjustedCapitalizedCost - residualValue) / leaseTerm;
        //Charges associated with purchasing vehicle (includes interest)
        double financeCost = (getVehicleSold().getPrice() + residualValue) * moneyFactor;

        monthlyPayment = depreciatingCost + financeCost + getLeaseFee();

        return monthlyPayment;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f|%.2f\n", "LEASE", getDate(), getCustomerName(), getCustomerEmail(), getVehicleSold().getVin(), getVehicleSold().getYear(), getVehicleSold().getMake(), getVehicleSold().getModel(), getVehicleSold().getVehicleType(), getVehicleSold().getColor(), getVehicleSold().getOdometer(), getVehicleSold().getPrice(), getExpectedEndValue(), getLeaseFee(), getTotalPrice(), getMonthlyPayment());
    }
}

package com.pluralsight.cars.models;

public class SalesContract extends Contract {
    private final double salesTax = 0.05;
    private final double recordingFee = 100.00;
    private final double processingFee = 0.00;
    private boolean isFinanced;
    private double downPayment;
    private double monthlyPayment;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    public double getSalesTax() {
        return salesTax * getVehicleSold().getPrice();
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        if (getVehicleSold().getPrice() < 10000) {
            return processingFee + 295.00;
        } else {
            return processingFee + 495.00;
        }
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getSalesTax() + getRecordingFee() + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {
        double interestRate;
        double loanAmount;
        int loanTerm;

        if (!isFinanced) {
            monthlyPayment = 0.0;

            return monthlyPayment;
        }
        //For financing option
        if (getVehicleSold().getPrice() >= 10000) {
            //Interest Rate = 4.25%
            interestRate = (4.25 / 12) / 100;
            loanAmount = getVehicleSold().getPrice() - getDownPayment();
            loanTerm = 48;
        } else {
            //Interest Rate = 5.25%
            interestRate = (5.25 / 12) / 100;
            loanAmount = getVehicleSold().getPrice() - getDownPayment();
            loanTerm = 24;
        }

        monthlyPayment = (loanAmount * interestRate * Math.pow(1 + interestRate, loanTerm)) / (Math.pow(1 + interestRate, loanTerm) - 1);
        return monthlyPayment;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f\n", "SALE", getDate(), getCustomerName(), getCustomerEmail(), getVehicleSold().getVin(), getVehicleSold().getYear(), getVehicleSold().getMake(), getVehicleSold().getModel(), getVehicleSold().getVehicleType(), getVehicleSold().getColor(), getVehicleSold().getOdometer(), getVehicleSold().getPrice(), getSalesTax(), getRecordingFee(), getProcessingFee(), getTotalPrice(), (isFinanced() ? "YES" : "NO"), getMonthlyPayment());
    }
}

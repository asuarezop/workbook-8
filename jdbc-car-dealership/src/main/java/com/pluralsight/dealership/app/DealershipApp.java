package com.pluralsight.dealership.app;

import com.pluralsight.dealership.services.UserInterface;

import java.io.IOException;

public class DealershipApp {
    public static void main(String[] args) throws IOException {
        //Declaring reference variable + instantiating a new UserInterface object
        UserInterface ui = new UserInterface();

        //Calling UserInterface display() for HomeScreen
        ui.showHomeScreen();
    }
}

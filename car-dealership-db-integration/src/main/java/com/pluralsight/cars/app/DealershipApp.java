package com.pluralsight.cars.app;

import com.pluralsight.cars.services.UserInterface;

import java.io.IOException;

public class DealershipApp {
    public static void main(String[] args) throws IOException {
        //Declaring reference variable + instantiating a new UserInterface object
        UserInterface ui = new UserInterface();

        //Calling UserInterface display() for HomeScreen
        ui.showHomeScreen();
    }
}

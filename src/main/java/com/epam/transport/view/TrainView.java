package com.epam.transport.view;

import com.epam.transport.entity.RollingStock;
import com.epam.transport.entity.passenger_wagon.PassengerWagon;

import java.util.List;

/**
 * The class is designed to output various kinds of information.
 */
public class TrainView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printTrain(List<RollingStock> rollingStocks) {
        for (RollingStock rollingStock : rollingStocks) {
            System.out.println(rollingStock);
        }
        System.out.println();
    }

    public void printPassengerWagons(List<PassengerWagon> passengerWagons) {
        for (PassengerWagon wagon : passengerWagons) {
            System.out.println(wagon);
        }
        System.out.println();
    }
}

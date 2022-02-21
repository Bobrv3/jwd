package com.epam.transport.logic.initializer.impl;

import com.epam.transport.logic.initializer.Initializer;
import com.epam.transport.entity.passenger_wagon.ComfortLevel;
import com.epam.transport.entity.passenger_wagon.PassengerWagon;
import com.epam.transport.entity.RollingStock;

/**
 * Intended for creating and initializing an object of the {@link PassengerWagon}
 */
public class PassVanInitializer implements Initializer {
    /**
     * Initializes the {@link PassengerWagon} object with the received properties
     *
     * @param properties an array containing lines describing the properties of the rolling stock unit and intended for
     *                   its initialization
     * @return a reference to an object of the {@link PassengerWagon}
     */
    @Override
    public RollingStock initialize(String[] properties) {
        int trackWidth = Integer.parseInt(properties[1]);
        int height = Integer.parseInt(properties[2]);
        int length = Integer.parseInt(properties[3]);
        int vMax = Integer.parseInt(properties[4]);
        int amountSeats = Integer.parseInt(properties[5]);
        int amountLuggage = Integer.parseInt(properties[6]);
        ComfortLevel comfortLevel = ComfortLevel.valueOf(properties[7]);

        return (new PassengerWagon(trackWidth, height, length, vMax, amountSeats,
                amountLuggage, comfortLevel));
    }
}

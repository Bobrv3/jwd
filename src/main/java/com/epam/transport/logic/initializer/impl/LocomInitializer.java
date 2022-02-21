package com.epam.transport.logic.initializer.impl;

import com.epam.transport.entity.locomotiv.Category;
import com.epam.transport.entity.passenger_wagon.PassengerWagon;
import com.epam.transport.logic.initializer.Initializer;
import com.epam.transport.entity.locomotiv.Locomotive;
import com.epam.transport.entity.locomotiv.EngineType;
import com.epam.transport.entity.RollingStock;

/**
 * Intended for creating and initializing an object of the {@link Locomotive}
 */
public class LocomInitializer implements Initializer {
    /**
     * Initializes the {@link Locomotive} object with the received properties
     *
     * @param properties an array containing lines describing the properties of the rolling stock unit and intended for
     *                   its initialization
     * @return a reference to an object of the {@link Locomotive}
     */
    @Override
    public RollingStock initialize(String[] properties) {
        int trackWidth = Integer.parseInt(properties[1]);
        int height = Integer.parseInt(properties[2]);
        int length = Integer.parseInt(properties[3]);
        int vMax = Integer.parseInt(properties[4]);
        EngineType engineType = EngineType.valueOf(properties[5]);
        int trustForth = Integer.parseInt(properties[6]);
        Category category = Category.valueOf(properties[7]);

        return (new Locomotive(trackWidth, height, length, vMax, engineType, trustForth, category));
    }
}

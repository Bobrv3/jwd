package com.epam.transport.logic.initializer;

import com.epam.transport.entity.RollingStock;

/**
 * The interface is implemented by classes designed to create and initialize an object of the {@link RollingStock}
 * class based on properties obtained from the string array <code>properties</code>.
 */
public interface Initializer {
    /**
     * Returns a new object of the {@link RollingStock} class initialized by the variables of the <code>properties</code>
     * array.
     *
     * @param properties an array containing lines describing the properties of the rolling stock unit and intended for
     *                   its initialization
     * @return an object of the RollingStock class initialized with <code>properties</code> values
     */
    RollingStock initialize(String[] properties);
}

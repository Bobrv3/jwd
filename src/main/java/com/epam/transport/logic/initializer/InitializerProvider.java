package com.epam.transport.logic.initializer;

import com.epam.transport.logic.initializer.impl.LocomInitializer;
import com.epam.transport.logic.initializer.impl.PassVanInitializer;

import java.util.HashMap;
import java.util.Map;

/**
 * The class is intended for working with objects that implement the {@link Initializer} interface
 */
public final class InitializerProvider {
    private static final Map<String, Initializer> ROLL_STOCK_TO_INITIALIZER = new HashMap<>();
    private static final InitializerProvider INSTANCE = new InitializerProvider();

    private InitializerProvider() {
        ROLL_STOCK_TO_INITIALIZER.put("PassengerWagon", new PassVanInitializer()) ;
        ROLL_STOCK_TO_INITIALIZER.put("Locomotive", new LocomInitializer()) ;
    }

    public static InitializerProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the initializer implementation corresponding to the received <code>initializerType</code>
     *
     * @param initializerType
     * @return an object of the required type <code>initializerType</code> that implements the initializer interface
     */
    public Initializer getInitializer(String initializerType) {
        Initializer initializer;
        initializer = ROLL_STOCK_TO_INITIALIZER.get(initializerType);

        return initializer;
    }
}

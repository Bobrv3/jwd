package com.epam.transport.logic;

import com.epam.transport.entity.passenger_wagon.PassengerWagon;
import com.epam.transport.entity.RollingStock;
import com.epam.transport.logic.comparator.ComfortLvlComparator;
import com.epam.transport.logic.initializer.Initializer;
import com.epam.transport.logic.initializer.InitializerProvider;
import com.epam.transport.logic.reader.ReaderProvider;
import com.epam.transport.logic.reader.RollingStockReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class describes the logic of working with rolling stock.
 */
public class RollingStockLogic {
    private final ReaderProvider readerProvider = ReaderProvider.getInstance();
    private final InitializerProvider initializerProvider =  InitializerProvider.getInstance() ;

    /**
     * Returns the total amount of passengers by checking if the rolling unit is a passenger wagon, so the
     * {@link PassengerWagon#getAmountPassengers()} method is executed and added to totalAmountPassengers.
     *
     * @param rollingStocks the list of rolling stock units for which needed to calculate the total amount of passengers
     * @return total amount of passengers in rollingStocks list or 0 if the argument "rollingStocks" is null
     */
    public int getTotalAmountPassengers(List<RollingStock> rollingStocks) {
        if (rollingStocks == null) {
            System.err.println("rollingStocks is null");
            return 0;
        }

        int totalAmountPassengers = 0;
        for (RollingStock rollingStock : rollingStocks) {
            if (rollingStock instanceof PassengerWagon) {
                totalAmountPassengers += ((PassengerWagon) rollingStock).getAmountPassengers();
            }
        }

        return totalAmountPassengers;
    }

    /**
     * Returns the total amount of luggage by checking if the rolling unit is a passenger wagon, so the
     * {@link PassengerWagon#getAmountLuggage()} method is executed and added to totalAmountLuggage.
     *
     * @param rollingStocks the list of rolling stock units for which needed to calculate the total amount of luggage
     * @return total amount of luggage in rollingStocks list or 0 if the argument "rollingStocks" is null
     */
    public int getTotalAmountLuggage(List<RollingStock> rollingStocks) {
        if (rollingStocks == null) {
            System.err.println("rollingStocks is null");
            return 0;
        }

        int totalAmountLuggage = 0;
        for (RollingStock rollingStock : rollingStocks) {
            if (rollingStock instanceof PassengerWagon) {
                totalAmountLuggage += ((PassengerWagon) rollingStock).getAmountLuggage();
            }
        }

        return totalAmountLuggage;
    }

    /**
     * Sorts passenger wagons by comfort level using a comparator {@link ComfortLvlComparator}.
     *
     * @param passengerWagons the list of passenger wagons which should be sorted
     * @return the list of passenger wagons sorted by comfort level or an empty list if the argument "passengerWagons"
     *          is null
     */
    public List<PassengerWagon> sortByComfortLvl(List<PassengerWagon> passengerWagons) {
        if (passengerWagons == null) {
            System.err.println("passengerWagons is null!");
            return Collections.emptyList();
        }

        ComfortLvlComparator comfortLvlComparator = new ComfortLvlComparator();

        return passengerWagons.stream()
                            .sorted(comfortLvlComparator)
                            .collect(Collectors.toList());

    }

    /**
     * Returns a list of passenger wagons from the argument <code>rollingStocks</code>.
     *
     * @param rollingStocks the list of rolling stock units for which needed to select passenger wagons
     * @return the list of passenger wagons or an empty list if the argument "rollingStocks" is null
     */
    public List<PassengerWagon> getPassengerWagons(List<RollingStock> rollingStocks) {
        if (rollingStocks == null) {
            System.err.println("rollingStocks is null!");
            return Collections.emptyList();
        }

        return rollingStocks.stream()
                            .filter(rollingStock -> rollingStock instanceof PassengerWagon)
                            .map(rollingStock -> (PassengerWagon) rollingStock)
                            .collect(Collectors.toList());
    }

    /**
     * Searches for passenger wagons from the <code>rollingStocks</code> with the amount of passengers from
     * the range [x,y].
     *
     * @param x left border of the range
     * @param y right border of the range
     * @param rollingStocks the list of rolling stock units for which needed to find wagons by range passengers
     * @return the list of wagons included in the specified range or an empty list if the argument "rollingStocks" is null
     */
    public List<RollingStock> findWagonsByRangePassengers(int x, int y, List<RollingStock> rollingStocks) {
        if (rollingStocks == null) {
            System.err.println("rollingStocks is null!");
            return Collections.emptyList();
        }

        List<RollingStock> wagonsFromRange;
        wagonsFromRange = rollingStocks.stream()
                                .filter(rollingStock -> rollingStock instanceof PassengerWagon)
                                .map(rollingStock -> (PassengerWagon) rollingStock)
                                .filter(rollingStock -> ((rollingStock.getAmountPassengers() >= x)
                                                            && (rollingStock.getAmountPassengers() <= y)))
                                .collect(Collectors.toList());

        return wagonsFromRange;
    }

    /**
     * Reads a list of lines from the file on the <code>path</code> containing the properties of the rolling stock. The
     * reading is performed using {@link RollingStockReader#readAllLines(String)}
     *
     * @param path the path to the file from which the data of rolling stock units will be read
     * @return the string list of rolling stock units or an empty list if the argument "path" is null
     */
    public List<String> readRollingStockFromFile(String path) {
        if (path == null) {
            return Collections.emptyList();
        }

        RollingStockReader fileRollingStockReader = readerProvider.getRollingStockReader();
        List<String> lines = fileRollingStockReader.readAllLines(path);

        return lines;
    }

    /**
     * Initializes the rolling stock from the list <code>rollingStockLines</code>. To do this, the rolling stock
     * properties are parsed from the string lines and passed to the corresponding initializer from
     * {@link InitializerProvider#getInitializer(String)}.
     *
     * @param rollingStockLines the list of lines containing information necessary for initialization of a rolling stock
     *                          unit
     * @return the list of initialized rolling stock units or an empty list if the argument "rollingStockLines" is null
     */
    public List<RollingStock> initializeRollingStock(List<String> rollingStockLines) {
        if (rollingStockLines == null) {
            System.err.println("rollingStockLines is null!");
            return Collections.emptyList();
        }

        String[] rollingStockProperties;
        List<RollingStock> rollingStocks = new ArrayList<>();

        for (String line : rollingStockLines) {
            rollingStockProperties = line.split(",\t");

            String rollingStockType = rollingStockProperties[0];

            Initializer rollStockInitializer = initializerProvider.getInitializer(rollingStockType);
            rollingStocks.add(rollStockInitializer.initialize(rollingStockProperties));
        }

        return rollingStocks;
    }
}

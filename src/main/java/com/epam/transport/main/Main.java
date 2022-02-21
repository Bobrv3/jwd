package com.epam.transport.main;

import com.epam.transport.entity.RollingStock;
import com.epam.transport.entity.Train;
import com.epam.transport.entity.passenger_wagon.PassengerWagon;
import com.epam.transport.logic.RollingStockLogic;
import com.epam.transport.view.TrainView;
import com.epam.transport.view.UserDataInput;

import java.util.List;

public class Main {
    private static final String ROLLING_STOCKS_TXT = "target/classes/rollingStocks.txt";
    private static final int MAX = 10_000;  // The maximum number for the range from 0 to max

    public static void main(String[] args) {
        RollingStockLogic rollingStockLogic = new RollingStockLogic();
        List<String> stringRollingStocks = rollingStockLogic.readRollingStockFromFile(ROLLING_STOCKS_TXT);
        List<RollingStock> rollingStocks = rollingStockLogic.initializeRollingStock(stringRollingStocks);
        int trainNumber = (int) (Math.random() * MAX);
        Train passengerTrain = new Train(trainNumber, rollingStocks);

        TrainView trainView = new TrainView();
        trainView.printMessage("Train contents:");
        trainView.printTrain(passengerTrain.getRollingStocks());

        int totalAmountPassengers = rollingStockLogic.getTotalAmountPassengers(passengerTrain.getRollingStocks());
        int totalAmountLuggage = rollingStockLogic.getTotalAmountLuggage(passengerTrain.getRollingStocks());

        trainView.printMessage("Total amount of passengers: " + totalAmountPassengers);
        trainView.printMessage("Total amount of baggage: " + totalAmountLuggage);

        List<PassengerWagon> passengerWagons =
                rollingStockLogic.getPassengerWagons(passengerTrain.getRollingStocks());
        passengerWagons = rollingStockLogic.sortByComfortLvl(passengerWagons);
        trainView.printMessage("Passenger wagons after sorting by comfort level:");
        trainView.printPassengerWagons(passengerWagons);

        trainView.printMessage("Enter the range [x;y] of the number of passengers: ");
        UserDataInput userDataInput = new UserDataInput();
        int x = userDataInput.enterNumber("x");
        int y = userDataInput.enterNumber("y");
        List<RollingStock> wagonsFromRange =
                rollingStockLogic.findWagonsByRangePassengers(x, y, passengerTrain.getRollingStocks());
        trainView.printTrain(wagonsFromRange);
    }
}

package com.epam.transport.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Train {
    private List<RollingStock> rollingStocks = new ArrayList<>();
    private int trainNumber;

    public Train() {
    }

    public Train(int trainNumber, List<RollingStock> rollingStocks) {
        this.rollingStocks = rollingStocks;
        this.trainNumber = trainNumber;
    }

    public List<RollingStock> getRollingStocks() {
        return rollingStocks;
    }

    public void setListRollingStock(List<RollingStock> rollingStocks) {
        this.rollingStocks = rollingStocks;
    }

    public void addRollingStock(RollingStock rollingStock) {
        rollingStocks.add(rollingStock);
    }

    public void deleteRollingStock(RollingStock rollingStock) {
        rollingStocks.remove(rollingStock);
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return trainNumber == train.trainNumber && Objects.equals(rollingStocks, train.rollingStocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollingStocks, trainNumber);
    }

    @Override
    public String toString() {
        return "Train{" +
                "rollingStocks=" + rollingStocks +
                ", trainNumber=" + trainNumber +
                '}';
    }
}

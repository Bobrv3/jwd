package com.epam.transport.entity.passenger_wagon;

import com.epam.transport.entity.RollingStock;

import java.util.Objects;

public class PassengerWagon extends RollingStock {
    private int amountPassengers;
    private int amountLuggage;
    private ComfortLevel comfortLevel;

    public PassengerWagon() {
    }

    public PassengerWagon(int trackWidth, int height, int length, int vmax
            , int amountSeats, int amountLuggage, ComfortLevel comfortLevel) {
        super(trackWidth, height, length, vmax);
        this.amountPassengers = amountSeats;
        this.amountLuggage = amountLuggage;
        this.comfortLevel = comfortLevel;
    }

    public int getAmountPassengers() {
        return amountPassengers;
    }

    public void setAmountPassengers(int amountPassengers) {
        this.amountPassengers = amountPassengers;
    }

    public int getAmountLuggage() {
        return amountLuggage;
    }

    public void setAmountLuggage(int amountLuggage) {
        this.amountLuggage = amountLuggage;
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PassengerWagon that = (PassengerWagon) o;
        return amountPassengers == that.amountPassengers && amountLuggage == that.amountLuggage && comfortLevel == that.comfortLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amountPassengers, amountLuggage, comfortLevel);
    }

    @Override
    public String toString() {
        return "PassengerWagon{" +
                "amountPassengers=" + amountPassengers +
                ", amountLuggage=" + amountLuggage +
                ", comfortLevel=" + comfortLevel +
                "} " + super.toString();
    }
}

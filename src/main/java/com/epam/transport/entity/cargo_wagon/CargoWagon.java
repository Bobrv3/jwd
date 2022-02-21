package com.epam.transport.entity.cargo_wagon;

import java.util.Objects;

public class CargoWagon {
    private int cargoWeight;
    private int volume;
    private ConstructionType constructionType;

    public CargoWagon() {
    }

    public CargoWagon(int cargoWeight, int volume, ConstructionType constructionType) {
        this.cargoWeight = cargoWeight;
        this.volume = volume;
        this.constructionType = constructionType;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoWagon that = (CargoWagon) o;
        return cargoWeight == that.cargoWeight && volume == that.volume && constructionType == that.constructionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargoWeight, volume, constructionType);
    }

    @Override
    public String toString() {
        return "CargoWagon{" +
                "cargoWeight=" + cargoWeight +
                ", volume=" + volume +
                ", constructionType=" + constructionType +
                '}';
    }
}

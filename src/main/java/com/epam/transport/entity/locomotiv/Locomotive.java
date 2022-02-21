package com.epam.transport.entity.locomotiv;

import com.epam.transport.entity.RollingStock;

import java.util.Objects;

public class Locomotive extends RollingStock {
    private EngineType engineType;
    private int thrustForce;
    private Category category;


    public Locomotive() {
    }

    public Locomotive(int trackWidth, int height, int length, int vmax, EngineType locomotiveType, int thrustForce, Category category) {
        super(trackWidth, height, length, vmax);
        this.engineType = locomotiveType;
        this.thrustForce = thrustForce;
        this.category = category;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public int getThrustForce() {
        return thrustForce;
    }

    public void setThrustForce(int thrustForce) {
        this.thrustForce = thrustForce;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Locomotive that = (Locomotive) o;
        return thrustForce == that.thrustForce && engineType == that.engineType && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), engineType, thrustForce, category);
    }

    @Override
    public String toString() {
        return "Locomotive{" +
                "engineType=" + engineType +
                ", thrustForce=" + thrustForce +
                ", category=" + category +
                "} " + super.toString();
    }
}

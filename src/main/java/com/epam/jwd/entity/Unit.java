package com.epam.jwd.entity;

import java.util.Objects;

public class Unit implements TextUnit, Cloneable {
    private String value;
    private UnitType type;
    private int startPositionIndex;  // Index of the first character of the Unit in the TextComposite

    public Unit() {
    }

    public Unit(String value, int startIndex) {
        this.value = value;
        this.startPositionIndex = startIndex;
    }

    public int getStartIndex() {
        return startPositionIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startPositionIndex = startIndex;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public UnitType getType() {
        return type;
    }

    @Override
    public void setType(UnitType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(value, unit.value) && type == unit.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "value='" + value + '\'' +
                ", type=" + type +
                ", startPositionIndex=" + startPositionIndex +
                '}';
    }

    @Override
    public Unit clone() throws CloneNotSupportedException {
        return (Unit) super.clone();
    }
}

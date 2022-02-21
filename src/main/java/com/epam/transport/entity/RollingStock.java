package com.epam.transport.entity;

import java.util.Objects;

public abstract class RollingStock {
    private int trackWidth;
    private int height;
    private int length;
    private int vMax;

    protected RollingStock() {
    }

    protected RollingStock(int trackWidth, int height, int width, int vmax) {
        this.trackWidth = trackWidth;
        this.height = height;
        this.length = width;
        vMax = vmax;
    }

    public int getTrackWidth() {
        return trackWidth;
    }

    public void setTrackWidth(int trackWidth) {
        this.trackWidth = trackWidth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getvMax() {
        return vMax;
    }

    public void setvMax(int vMax) {
        this.vMax = vMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RollingStock that = (RollingStock) o;
        return trackWidth == that.trackWidth && height == that.height && length == that.length && vMax == that.vMax;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackWidth, height, length, vMax);
    }

    @Override
    public String toString() {
        return "RollingStock{" +
                "trackWidth=" + trackWidth +
                ", height=" + height +
                ", width=" + length +
                ", Vmax=" + vMax +
                '}';
    }
}

package com.epam.transport.logic.comparator;

import com.epam.transport.entity.passenger_wagon.PassengerWagon;

import java.util.Comparator;

/**
 * The class describes a comparator for comparing passenger wagons by comfort level from the highest (luxury) to the
 * lowest (common).
 * <p>
 * Here we move all null elements to the end of the collection. To do this, the comparator considers null to be greater
 * than non-zero values. When both are null, they are considered equal.
 */
public class ComfortLvlComparator implements Comparator<PassengerWagon> {

    @Override
    public int compare(PassengerWagon o1, PassengerWagon o2) {
        if (o1 == null) {
            return o2 == null ? 0 : 1;
        }
        else if (o2 == null) {
            return -1;
        }

        return o1.getComfortLevel().ordinal() - o2.getComfortLevel().ordinal();
    }
}

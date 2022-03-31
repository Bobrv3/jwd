package com.epam.jwd.logic;

import com.epam.jwd.entity.TextComposite;
import com.epam.jwd.entity.TextUnit;
import com.epam.jwd.entity.UnitType;

import java.util.ArrayList;
import java.util.List;

/**
 * The class provides methods for working with text units.
 */
public class TextUnitLogic {
    /**
     * Returns a list of text units of the specified type received from a text object.
     * <br><br>
     * The incoming text object is checked for belonging to the desired type. When true, a list with this object
     * is returned. Otherwise, we get a list of text units that make up a text object and check their type. If the type
     * matches the one you are looking for, add this unit to the list. otherwise, if it is a text object, then we get a
     * list of text units that it consists of and for each recursively call the <code>getUnits<code/> method.
     *
     * @param type          type of text unit to get from the <code>textObj</code>
     * @param textComposite a text unit from which it is necessary to get text units of a given <code>type</code>
     * @return a list of text units of a given <code>type</code> received from the <code>textObj</code>
     */
    public List<TextUnit> getUnits(UnitType type, TextComposite textComposite) {
        List<TextUnit> units = new ArrayList<>();

        if (textComposite.getType() != null && textComposite.getType().equals(type)) {
            units.add(textComposite);
            return units;
        }

        for (TextUnit textUnit : textComposite.getTextUnits()) {
            if (textUnit.getType().equals(type)) {
                units.add(textUnit);
            } else if (textUnit instanceof TextComposite) {
                units.addAll(getUnits(type, (TextComposite) textUnit));
            }
        }

        return units;
    }

    /**
     * Returns a list of found text units of the required type corresponding to the regular expression.
     * <br><br>
     * Each text unit of the required type obtained from a text object is checked for its compliance with a regular
     * expression. If it matches, then add this element to the list.
     *
     * @param type          type of text unit to find in the <code>textObj</code>
     * @param textComposite a text unit from which it is necessary to find text units of a given <code>type</code>
     * @param regex         a regular expression for searching for text unit
     * @return the list of found text units of the required type corresponding to the regular expression
     * @see #getUnits(UnitType, TextComposite)
     */
    public List<TextUnit> findUnitsByRegex(UnitType type, TextComposite textComposite, String regex) {
        List<TextUnit> foundUnits = new ArrayList<>();

        for (TextUnit unit : getUnits(type, textComposite)) {
            if (unit.getValue().matches(regex)) {
                foundUnits.add(unit);
            }
        }

        return foundUnits;
    }
}

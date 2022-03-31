package com.epam.jwd.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextUnit, Cloneable {
    private List<TextUnit> textUnits;
    private UnitType type;

    public TextComposite() {
        textUnits = new ArrayList<>();
    }

    public List<TextUnit> getTextUnits() {
        return textUnits;
    }

    public void setTextUnits(List<TextUnit> textUnits) {
        this.textUnits = textUnits;
    }

    public void addTextUnit(TextUnit textUnit) {
        textUnits.add(textUnit);
    }

    public void addAllTextUnits(List<TextUnit> textUnitList) {
        textUnits.addAll(textUnitList);
    }

    public void removeTextUnit(TextUnit unit) {
        if (textUnits.contains(unit)) {
            textUnits.remove(unit);
        } else {
            for (TextUnit textUnit : textUnits) {
                if (textUnit instanceof TextComposite) {
                    ((TextComposite) textUnit).removeTextUnit(unit);
                }
            }
        }
    }

    @Override
    public String getValue() {
        StringBuilder textUnit = new StringBuilder();
        for (TextUnit unit : textUnits) {
            textUnit.append(unit.getValue());
        }
        return textUnit.toString();
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
        TextComposite textComposite = (TextComposite) o;
        return Objects.equals(textUnits, textComposite.textUnits) && type == textComposite.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(textUnits, type);
    }

    @Override
    public String toString() {
        return "Text{" +
                "textUnits=" + textUnits +
                ", type=" + type +
                '}';
    }

    @Override
    public TextComposite clone() throws CloneNotSupportedException {
        TextComposite copy = new TextComposite();
        copy.setType(type);

        for (TextUnit textUnit : textUnits) {
            if (textUnit instanceof TextComposite) {
                copy.addTextUnit(((TextComposite) textUnit).clone());
            } else {
                copy.addTextUnit(((Unit) textUnit).clone());
            }
        }

        return copy;
    }
}

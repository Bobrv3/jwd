package com.epam.jwd.logic.parser;

import com.epam.jwd.entity.TextComposite;
import com.epam.jwd.entity.TextUnit;
import com.epam.jwd.entity.Unit;
import com.epam.jwd.entity.UnitType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class is designed for parsing text into text units according to a regular expression.
 */
public abstract class Parser {
    private static final Logger logger = LogManager.getLogger(Parser.class);
    private final String regex;
    protected UnitType typeForParse;
    private Parser next;

    protected Parser(UnitType typeForParse, String regex) {
        this.typeForParse = typeForParse;
        this.regex = regex;
    }

    public Parser getNext() {
        return next;
    }

    public void setNext(Parser next) {
        this.next = next;
    }

    public String getRegex() {
        return regex;
    }

    /**
     * Returns a text object matching a regular expression.
     * <br><br>
     * A text object can consist of text units or be an independent unit.
     * Based on this, if there is a link to the next Parser, then, for each match to the regular expression of the
     * input text, this Parser gets a text unit from next Parser and add it to the text object. Otherwise, for each
     * match to the regular expression of the input text, this Parser adds the found text unit to the text object.
     *
     * @param inputText text for parsing
     * @return a text object matching a regular expression
     */
    public List<TextUnit> parse(String inputText) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);

        List<TextUnit> textUnits = new ArrayList<>();

        if (next != null) {
            while (matcher.find()) {
                TextComposite compositeUnit = new TextComposite();
                compositeUnit.setType(typeForParse);

                List<TextUnit> content = next.parse(matcher.group());
                compositeUnit.setTextUnits(content);
                logger.debug("Parsed {}:\n{}", compositeUnit.getType(), compositeUnit.getValue());
                textUnits.add(compositeUnit);
            }
        } else {
            while (matcher.find()) {
                Unit unit = new Unit(matcher.group(), matcher.start());
                unit.setType(typeForParse);

                logger.debug("Parsed {}:\n{}", unit.getType(), unit.getValue());
                textUnits.add(unit);
            }
        }

        return textUnits;
    }
}

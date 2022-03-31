package com.epam.jwd.logic.parser.impl;

import com.epam.jwd.entity.TextUnit;
import com.epam.jwd.entity.Unit;
import com.epam.jwd.entity.UnitType;
import com.epam.jwd.logic.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class for parsing code blocks.
 */
public class CodeBlockParser extends Parser {
    private static final Logger logger = LogManager.getLogger(CodeBlockParser.class);
    private static final UnitType TYPE_FOR_PARSE = UnitType.CODE_BLOCK;

    public CodeBlockParser() {
        super(TYPE_FOR_PARSE, UnitType.CODE_BLOCK.getRegex());
    }

    /**
     * Returns a text object matching a regular expression.
     * <br><br>
     * If the code block matches within the input text, this parser adds it to the text object. Otherwise, passes the
     * input text to the next parser.
     *
     * @param inputText text for parsing
     * @return returns a text object - a code block or a text object received from the next parser, if available
     */
    @Override
    public List<TextUnit> parse(String inputText) {
        Pattern pattern = Pattern.compile(UnitType.CODE_BLOCK.getRegex());
        Matcher matcher = pattern.matcher(inputText);

        List<TextUnit> textUnits = new ArrayList<>();

        if (matcher.find()) {
            Unit unit = new Unit(matcher.group(), matcher.start());
            unit.setType(TYPE_FOR_PARSE);

            logger.debug("Parsed {}:\n{}", unit.getType(), unit.getValue());
            textUnits.add(unit);
        } else {
            textUnits = super.getNext().parse(inputText);
        }

        return textUnits;
    }
}

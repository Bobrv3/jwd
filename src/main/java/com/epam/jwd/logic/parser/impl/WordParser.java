package com.epam.jwd.logic.parser.impl;

import com.epam.jwd.entity.UnitType;
import com.epam.jwd.logic.parser.Parser;

/**
 * The class for parsing words.
 */
public class WordParser extends Parser {
    private static final UnitType TYPE_FOR_PARSE = UnitType.WORD;

    public WordParser() {
        super(TYPE_FOR_PARSE, UnitType.WORD.getRegex());
    }
}

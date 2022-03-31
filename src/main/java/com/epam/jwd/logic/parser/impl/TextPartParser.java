package com.epam.jwd.logic.parser.impl;

import com.epam.jwd.entity.UnitType;
import com.epam.jwd.logic.parser.Parser;

/**
 * The class for parsing parts of text.
 */
public class TextPartParser extends Parser {
    private static final UnitType TYPE_FOR_PARSE = UnitType.TEXT_PART;

    public TextPartParser() {
        super(TYPE_FOR_PARSE, UnitType.TEXT_PART.getRegex());
    }
}

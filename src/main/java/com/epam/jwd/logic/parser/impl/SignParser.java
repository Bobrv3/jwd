package com.epam.jwd.logic.parser.impl;

import com.epam.jwd.entity.UnitType;
import com.epam.jwd.logic.parser.Parser;

/**
 * The class for parsing punctuation signs.
 */
public class SignParser extends Parser {
    private static final UnitType TYPE_FOR_PARSE = UnitType.SIGN;

    public SignParser() {
        super(TYPE_FOR_PARSE, UnitType.SIGN.getRegex());
    }
}

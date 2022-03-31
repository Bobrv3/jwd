package com.epam.jwd.logic.parser.impl;

import com.epam.jwd.entity.UnitType;
import com.epam.jwd.logic.parser.Parser;

/**
 * The class for parsing paragraphs.
 */
public class ParagraphParser extends Parser {
    private static final UnitType TYPE_FOR_PARSE = UnitType.PARAGRAPH;

    public ParagraphParser() {
        super(TYPE_FOR_PARSE, UnitType.PARAGRAPH.getRegex());
    }
}

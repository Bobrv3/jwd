package com.epam.jwd.entity;

public enum UnitType {
    TEXT_PART("(?m)(^.*\\{[\\w\\W]+?^\\}[\\r\\n]+)|(?m)(^.*?[\\r\\n]+)|(?m)(^.*?[\\r\\n]*$)"),
    PARAGRAPH("(?m)(^.*?[\\r\\n]+)|(?m)(^.*?[\\r\\n]*$)"),
    CODE_BLOCK("(?m)^.*\\{[\\w\\W]+?^\\}[\\r\\n]+"),
    SENTENCE("([ A-Z\\d]+.*?[\\.!?\\r?\\n]+?[\\r\\n]*)"),
    WORD("\\w+"),
    SIGN("[\\W\\s]");

    private final String regex;

    UnitType(String regexToParse) {
        regex = regexToParse;
    }

    public String getRegex() {
        return regex;
    }
}

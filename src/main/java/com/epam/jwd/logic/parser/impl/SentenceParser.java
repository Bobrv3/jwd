package com.epam.jwd.logic.parser.impl;

import com.epam.jwd.entity.TextComposite;
import com.epam.jwd.entity.TextUnit;
import com.epam.jwd.entity.Unit;
import com.epam.jwd.entity.UnitType;
import com.epam.jwd.logic.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The class for parsing sentences.
 */
public class SentenceParser extends Parser {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);
    private static final UnitType TYPE_FOR_PARSE = UnitType.SENTENCE;
    private final Parser wordParser = new WordParser();
    private final Parser signParser = new SignParser();

    public SentenceParser() {
        super(TYPE_FOR_PARSE, UnitType.SENTENCE.getRegex());
    }

    /**
     * Returns a text object matching a regular expression.
     * <br><br>
     * For each sentence found, receives punctuation signs and words using the appropriate parsers. Adds them to the
     * sentence. Sorts them by the <code>from<code/> variable - the position of the beginning of the text element.
     *
     * @param inputText text for parsing
     * @return returns a text object - sentence, containing punctuation signs and words
     */
    @Override
    public List<TextUnit> parse(String inputText) {
        Pattern pattern = Pattern.compile(super.getRegex());
        Matcher matcher = pattern.matcher(inputText);

        List<TextUnit> sentences = new ArrayList<>();

        while (matcher.find()) {
            TextComposite sentence = new TextComposite();
            sentence.setType(TYPE_FOR_PARSE);

            List<TextUnit> signs = signParser.parse(matcher.group());
            sentence.addAllTextUnits(signs);

            List<TextUnit> words = wordParser.parse(matcher.group());
            sentence.addAllTextUnits(words);

            List<TextUnit> sortedUnitsByPosition = sentence.getTextUnits().stream()
                    .sorted(Comparator.comparingInt(unit -> ((Unit) unit).getStartIndex()))
                    .collect(Collectors.toList());

            sentence.setTextUnits(sortedUnitsByPosition);
            logger.debug("Parsed {}:\n{}", sentence.getType(), sentence.getValue());
            sentences.add(sentence);
        }

        return sentences;

    }
}

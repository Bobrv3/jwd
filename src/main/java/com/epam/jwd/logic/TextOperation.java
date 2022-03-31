package com.epam.jwd.logic;

import com.epam.jwd.entity.TextComposite;
import com.epam.jwd.entity.TextUnit;
import com.epam.jwd.entity.UnitType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class provides three operations from the source task for text processing.
 */
public class TextOperation {
    private static final String QUESTION_SENTENCE = ".*\\?\\s*?$";
    private static final String WORD_BY_LENGTH = "\\b[A-Za-z]{%d}\\b";
    private static final String WORD_BY_LENGTH_START_CONSONANT = "\\b[A-Za-z&&[^AaEeIiOoUu]][A-Za-z]{%d}\\b";  // [A-Za-z&&[^AaEeIiOoUu]] - First letter:consonant, [A-Za-z]{%d} - other letters in some quantity
    private static final int FIRST_LETTER = 1;
    private static final Logger logger = LogManager.getLogger(TextOperation.class);
    private final TextUnitLogic textUnitLogic = new TextUnitLogic();

    /**
     * In all question sentences of the text, method finds and prints words of a given length without repetition.
     * (№4 in the task).
     * <br><br>
     * Method gets a list of question sentences. For each sentence from the list, method gets a list of words of a
     * given length and adds them to the set (to exclude repetitions).
     *
     * @param textComposite text object for the operation
     * @param wordLength    word length
     * @return a list of found words(without repetitions) of a given length, in all question sentences of the text
     * @see TextUnitLogic#findUnitsByRegex(UnitType, TextComposite, String)
     */
    public Set<TextUnit> firstOperation(TextComposite textComposite, int wordLength) {
        String wordWithLength = String.format(WORD_BY_LENGTH, wordLength); // a regular expression for searching words of the required length

        List<TextUnit> sentences = textUnitLogic.findUnitsByRegex(UnitType.SENTENCE, textComposite, QUESTION_SENTENCE);
        logger.debug("Found question sentences:");
        for (TextUnit sentence : sentences) {
            logger.debug("{}", sentence.getValue());
        }

        Set<TextUnit> foundWordsSet = new HashSet<>();
        for (TextUnit sentence : sentences) {
            List<TextUnit> foundWordsList = textUnitLogic.findUnitsByRegex(UnitType.WORD, (TextComposite) sentence, wordWithLength);
            foundWordsSet.addAll(foundWordsList);
        }

        return foundWordsSet;
    }

    /**
     * In each sentence of the text swaps the first word with the last, without changing the length of the sentence
     * (№5 in the task).
     * <br><br>
     * Gets a list of text sentences. For each sentence from the list, it gets a list of words contained in it and
     * swaps the first word with the last one.
     *
     * @param textComposite text object for the operation
     * @see TextUnitLogic#findUnitsByRegex(UnitType, TextComposite, String)
     * @see TextUnitLogic#getUnits(UnitType, TextComposite)
     */
    public void secondOperation(TextComposite textComposite) {
        List<TextUnit> sentences = textUnitLogic.getUnits(UnitType.SENTENCE, textComposite);

        for (TextUnit sentence : sentences) {
            List<TextUnit> words = textUnitLogic.getUnits(UnitType.WORD, (TextComposite) sentence);
            List<TextUnit> sentenceMembers = ((TextComposite) sentence).getTextUnits();
            Collections.swap(sentenceMembers, sentenceMembers.indexOf(words.get(0)),
                    sentenceMembers.lastIndexOf(words.get(words.size() - 1)));
        }
    }

    /**
     * Removes from the text all words of a given length starting with a consonant letter (№12 in the task).
     * <br><br>
     * Receives a list of words of the text of the required length. Removes them from the text.
     *
     * @param textComposite text object for the operation
     * @param wordLength    word length
     * @see TextUnitLogic#findUnitsByRegex(UnitType, TextComposite, String)
     */
    public void thirdOperation(TextComposite textComposite, int wordLength) {
        String regex = String.format(WORD_BY_LENGTH_START_CONSONANT, wordLength - FIRST_LETTER);
        List<TextUnit> foundWords = textUnitLogic.findUnitsByRegex(UnitType.WORD, textComposite, regex);

        for (TextUnit word : foundWords) {
            textComposite.removeTextUnit(word);
        }
    }
}


package com.epam.jwd;

import com.epam.jwd.entity.TextComposite;
import com.epam.jwd.entity.TextUnit;
import com.epam.jwd.logic.TextOperation;
import com.epam.jwd.logic.parser.Parser;
import com.epam.jwd.logic.parser.impl.CodeBlockParser;
import com.epam.jwd.logic.parser.impl.ParagraphParser;
import com.epam.jwd.logic.parser.impl.SentenceParser;
import com.epam.jwd.logic.parser.impl.TextPartParser;
import com.epam.jwd.logic.reader.TextReader;
import com.epam.jwd.logic.reader.TextReaderException;
import com.epam.jwd.logic.writer.TextWriter;
import com.epam.jwd.logic.writer.TextWriterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Main {
    private static final String TEXT_FOR_PARSE = "./src/main/resources/TextForParse.txt";
    private static final String PARSED_TEXT = "./src/main/resources/ParsedText.txt";
    private static final String SECOND_OPERATION_RESULT = "./src/main/resources/SecondOperationResult.txt";
    private static final String THIRD_OPERATION_RESULT = "./src/main/resources/ThirdOperationResult.txt";
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws CloneNotSupportedException {
        String text;
        TextReader reader = new TextReader();
        try {
            text = reader.readText(TEXT_FOR_PARSE);
            logger.info("Source text:\n{}", text);
        } catch (TextReaderException e) {
            logger.error(e.getMessage());
            return;
        }

        TextComposite parsedText = new TextComposite();
        parsedText.setTextUnits(getParserChain().parse(text));

        TextWriter writer = new TextWriter();
        try {
            writer.write(parsedText.getValue(), PARSED_TEXT);
            logger.info("Parsed Text:\n{}", parsedText.getValue());
        } catch (TextWriterException e) {
            logger.error(e.getMessage());
            return;
        }

        logger.debug("Are the texts the same?: {}", parsedText.getValue().equals(text));

        TextOperation textOperation = new TextOperation();
        int wordLength = 3;

        // FIRST TEXT OPERATION
        Set<TextUnit> foundWords = textOperation.firstOperation(parsedText, wordLength);
        logger.info("Found words(without repetitions) of a given length={}, in all question sentences of the text:",
                wordLength);
        for (TextUnit word : foundWords) {
            logger.info(word.getValue());
        }

        // SECOND TEXT OPERATION
        TextComposite cloneParsedText = parsedText.clone();
        textOperation.secondOperation(cloneParsedText);
        try {
            writer.write(cloneParsedText.getValue(), SECOND_OPERATION_RESULT);
            logger.info("The text, after the first word has been swapped with the last one in each sentence:\n{}",
                    parsedText.getValue());
        } catch (TextWriterException e) {
            logger.error(e.getMessage());
            return;
        }

        // THIRD TEXT OPERATION
        cloneParsedText = parsedText.clone();
        textOperation.thirdOperation(cloneParsedText, wordLength);
        try {
            writer.write(cloneParsedText.getValue(), THIRD_OPERATION_RESULT);
            logger.info("Text after deleting all words of a given length={} starting with a consonant letter:\n{}",
                    wordLength, parsedText.getValue());
        } catch (TextWriterException e) {
            logger.error(e.getMessage());
        }
    }

    private static Parser getParserChain() {
        TextPartParser textPartParser = new TextPartParser();
        CodeBlockParser codeBlockParser = new CodeBlockParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();

        textPartParser.setNext(codeBlockParser);
        codeBlockParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);

        return textPartParser;
    }
}

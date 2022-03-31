package com.epam.jwd.logic.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {
    private static final Logger logger = LogManager.getLogger(TextReader.class);
    private static final int LAST_LINE_SEPARATOR = 2; // last line separator consist of 2 symbols [\r\n]

    /**
     * Reads text from a file.
     *
     * @param pathToText path to the file from which the text is being read
     * @return the text that was read
     * @throws TextReaderException called if reading from a file fails or if the file is not found
     */
    public String readText(String pathToText) throws TextReaderException {
        StringBuilder text = new StringBuilder();

        try (FileReader fileReader = new FileReader(pathToText);
             BufferedReader reader = new BufferedReader(fileReader)) {
            while (reader.ready()) {
                text.append(reader.readLine());
                text.append(System.lineSeparator());
            }
            text.trimToSize();
            text = new StringBuilder(text.substring(0, text.capacity() - LAST_LINE_SEPARATOR));
        } catch (FileNotFoundException e) {
            logger.error("File: {} - not found.", pathToText);
            throw new TextReaderException("File: {} - not found.", e);
        } catch (IOException e) {
            logger.error("Cannot read file: {}", pathToText);
            throw new TextReaderException("Cannot read file: {}", e);
        }

        return text.toString();
    }
}

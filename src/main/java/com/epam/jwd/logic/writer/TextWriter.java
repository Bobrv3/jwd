package com.epam.jwd.logic.writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The class is intended for writing a text unit to a file.
 */
public class TextWriter {
    private static final Logger logger = LogManager.getLogger(TextWriter.class);

    /**
     * Writes a string to a file.
     *
     * @param value      the string to be written
     * @param pathToFile the path to the file in which the string is written
     * @throws TextWriterException called in case of failure of writing to the file
     */
    public void write(String value, String pathToFile) throws TextWriterException {
        try (FileWriter fileWriter = new FileWriter(pathToFile)) {
            fileWriter.write(value);
        } catch (IOException e) {
            logger.error("Cannot write to file: {}", pathToFile);
            throw new TextWriterException("Cannot write to file.", e);
        }
    }
}

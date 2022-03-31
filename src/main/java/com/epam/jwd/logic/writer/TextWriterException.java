package com.epam.jwd.logic.writer;

public class TextWriterException extends Exception {
    public TextWriterException() {
        super();
    }

    public TextWriterException(String message) {
        super(message);
    }

    public TextWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextWriterException(Throwable cause) {
        super(cause);
    }
}

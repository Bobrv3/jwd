package com.epam.transport.logic.reader;

import com.epam.transport.logic.reader.impl.FileRollingStockReader;

/**
 * The class is intended for working with objects that implement the {@link RollingStockReader} interface
 */
public final class ReaderProvider {
    private static final ReaderProvider INSTANCE = new ReaderProvider();
    private RollingStockReader rollingStockReader = new FileRollingStockReader();

    private ReaderProvider() {
    }

    public static ReaderProvider getInstance() {
        return INSTANCE;
    }

    public RollingStockReader getRollingStockReader() {
        return rollingStockReader;
    }

    public void setRollingStockReader(RollingStockReader rollingStockReader) {
        this.rollingStockReader = rollingStockReader;
    }
}

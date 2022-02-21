package com.epam.transport.logic.reader;

import java.util.List;

/**
 * The interface is implemented by classes designed to read rolling stock from a data source
 */
public interface RollingStockReader {
     /**
      * Returns an array of lines contained in the data source.
      *
      * @param path the path to the data source
      * @return list of read lines
      */
     List<String> readAllLines(String path);
}

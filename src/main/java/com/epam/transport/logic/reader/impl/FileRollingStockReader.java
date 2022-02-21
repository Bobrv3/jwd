package com.epam.transport.logic.reader.impl;

import com.epam.transport.logic.reader.RollingStockReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Intended to read a file by lines.
 * <p>
 * The line represents properties describing a unit of rolling stock, separated by ",\t"
 */
public class FileRollingStockReader implements RollingStockReader {
    @Override
    public List<String> readAllLines(String path) {
        if (path == null) {
            return Collections.emptyList();
        }

        List<String> lines = new ArrayList<>();

        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {

            while (reader.ready()) {
                String line = reader.readLine();

                if (line.isEmpty()) {
                    continue;
                }

                lines.add(line);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return lines;
    }

}

package utils.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.Reader;

public class FileReader {
    public static Reader getFileReader(String filepath) {
        try {
            return new BufferedReader(new java.io.FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(String.format("Could not find file: %s%n", filepath));
        }
    }
}
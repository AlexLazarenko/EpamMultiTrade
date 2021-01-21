package edu.epam.multitrade.reader;

import edu.epam.multitrade.utility.PropertyLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MultiTradeFileReader {
    private static final Logger logger = LogManager.getLogger(MultiTradeFileReader.class);

    public List<String> readFromFile(String dir) {
        List<String> lines = new ArrayList<>();
        File file = new File(PropertyLoader.getProperty(dir));
        try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        logger.info("String array with lines from file: " + lines);
        return lines;
    }
}

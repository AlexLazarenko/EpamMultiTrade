package edu.epam.multitrade.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class PropertyLoader {
    public final static String PROPERTY_PATH = "src/main/resources/property/config.properties";
    private static final Logger logger = LogManager.getLogger(PropertyLoader.class);

    public static String getProperty(String key) {
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTY_PATH)) {
            property.load(fis);
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        String dir = property.getProperty(key);
        logger.info("Current directory is " + dir);
        return dir;
    }
}

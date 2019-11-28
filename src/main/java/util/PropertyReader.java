package util;

import service.DBException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyReader {
    //    private static PropertyReader INSTANCE;
    private static final String fileName = "Dao.properties";
    private static Properties properties;

    private final static Logger logger = Logger.getLogger(PropertyReader.class.getName());

//    private PropertyReader() {
//        this.properties = readProp();
//    }

//    public static PropertyReader getINSTANCE() {
//        if (INSTANCE == null) {
//            INSTANCE = new PropertyReader();
//        }
//        return INSTANCE;
//    }

    public static String getProperty(String propertyName) {
        if (properties == null) {
            properties = readProp();
        }
        return properties.getProperty(propertyName);
    }

    private static Properties readProp() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream(fileName)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            logger.severe("File issues...");
        }

        return properties;
    }
}

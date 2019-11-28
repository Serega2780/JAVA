package util;

import service.DBException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyReader {
    private static PropertyReader INSTANCE;
    private static final String fileName = "Dao.properties";
//    private final Properties properties;

    final static Logger logger = Logger.getLogger(PropertyReader.class.getName());

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
        return readProp().getProperty(propertyName);
    }

    private static Properties readProp() {
        Properties plan = new Properties();
        File f = new File(fileName);
        logger.info(f.getAbsolutePath());
        logger.info(String.format("Present Project Directory : %s", System.getProperty("user.dir")));

        try (FileInputStream fin = new FileInputStream(fileName)) {
            plan.load(fin);
        } catch (IOException e) {
            logger.severe("File issues...");
        }
        return plan;
    }
}

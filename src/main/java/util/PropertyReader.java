package util;

import service.DBException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final String fileName = "Dao.properties";

    public static String getProperty(String propertyName) {

    }

    private static Properties ReadProp() throws DBException {
        Properties plan = new Properties();
        File f = new File(fileName);
        System.out.println(f.getAbsolutePath());
        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));

        try (FileInputStream fin = new FileInputStream(fileName)) {
            plan.load(fin);
        } catch (IOException e) {
            throw new DBException("File issues...");
        }
        return plan;
    }
}

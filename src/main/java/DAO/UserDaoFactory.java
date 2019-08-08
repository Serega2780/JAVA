package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class UserDaoFactory {
    public abstract UserDAO createDAO();

    private static final String fileName = "Dao.properties";

    public static UserDaoFactory getDaoFactory() {
        if (ReadProp().getProperty("DAO").equalsIgnoreCase("jdbc")) {
            return new UserDaoFactoryImplJDBC();
        } else if (ReadProp().getProperty("DAO").equalsIgnoreCase("hibernate")) {
            return new UserDaoFactoryImplHibernate();
        }
        return null;
    }

    private static Properties ReadProp() {
        Properties plan = new Properties();
        File f = new File(fileName);
        System.out.println(f.getAbsolutePath());
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));

        try (FileInputStream fin = new FileInputStream(fileName)) {
            plan.load(fin);
        } catch (IOException e) {
            System.out.println("File issues...");

        }
        return plan;
    }
}

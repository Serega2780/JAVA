package service;

import Factory.UserDaoFactory;
import Factory.UserDaoFactoryImplHibernate;
import Factory.UserDaoFactoryImplJDBC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserFactoryHelper {
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

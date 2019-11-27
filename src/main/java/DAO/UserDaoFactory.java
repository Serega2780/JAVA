package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class UserDaoFactory {
    public abstract UserDAO createDAO();



    public static UserDaoFactory getDaoFactory() {
        if (ReadProp().getProperty("DAO").equalsIgnoreCase("jdbc")) {
            return new UserDaoFactoryImplJDBC();
        } else if (ReadProp().getProperty("DAO").equalsIgnoreCase("hibernate")) {
            return new UserDaoFactoryImplHibernate();
        }
        return null;
    }


}

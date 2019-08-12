package Factory;

import DAO.UserDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class UserDaoFactory {
    public abstract UserDAO createDAO();




}

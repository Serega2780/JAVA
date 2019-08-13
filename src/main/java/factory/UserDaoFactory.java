package factory;

import dao.UserDAO;

public abstract class UserDaoFactory {
    public abstract UserDAO createDAO();




}

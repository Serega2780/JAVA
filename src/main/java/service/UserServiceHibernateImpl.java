package service;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.logging.Logger;

public class UserServiceHibernateImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceHibernateImpl.class.getName());
    private UserDAO userDAO;

    public UserServiceHibernateImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }



    @Override
    public void createUser(User user) {
        try {
            userDAO.insertUser(user);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    public User selectUser(int id) {
        try {
            return userDAO.selectUser(id);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        try {
            return userDAO.selectAllUsers();
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            return userDAO.deleteUser(id);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
        try {
            userDAO.updateUser(user);
        } catch (DBException e) {
            logger.severe(e.getMessage());
        }
    }
}

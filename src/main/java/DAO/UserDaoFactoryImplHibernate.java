package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import service.DBHelper;

public class UserDaoFactoryImplHibernate extends UserDaoFactory {
    private UserDAO userDAO;
    private static SessionFactory sessionFactory;

    @Override
    public UserDAO createDAO() {
        if (this.userDAO == null) {
            this.userDAO = new UserDaoImplHibernate(getSessionFactory().openSession());
        }
        return this.userDAO;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }
}

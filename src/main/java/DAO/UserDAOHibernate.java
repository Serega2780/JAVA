package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.DBHelper;

import java.util.List;

public class UserDAOHibernate implements UserDAO {
    private SessionFactory sessionFactory;
    private Session session;

    public UserDAOHibernate(SessionFactory sessionFactory) {
        this.session = session;
    }

    @Override
    public void insertUser(User user) {
        session = DBHelper.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User selectUser(int id) {
        session = DBHelper.getInstance().getSessionFactory().openSession();
        User user;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE id = :id");
        query.setParameter("id", id);
        user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        session = DBHelper.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return allUsers;
    }

    @Override
    public boolean deleteUser(int id) {
        session = DBHelper.getInstance().getSessionFactory().openSession();
        boolean rowDeleted = false;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", id);
        rowDeleted = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return rowDeleted;
    }

    @Override
    public void updateUser(User user) {
        session = DBHelper.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }
}

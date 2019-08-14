package main.dao;

import main.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String SELECT_USER_BY_ID_HIBERNATE = "FROM User WHERE id = :id";
    // private static final String SELECT_NOT_ADMINS = "FROM User WHERE role = :role";
    // private static final String SELECT_USER_BY_ROLE = "FROM User WHERE name = :name AND password = :password";
    private static final String DELETE_USERS_HIBERNATE = "DELETE FROM User WHERE id = :id";
    private Session session;

    public UserDAOImpl(Session session) {
        super();
        this.session = session;
    }

    @Override
    public void insertUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(SELECT_USER_BY_ID_HIBERNATE);
        query.setParameter("id", id);
        if (!query.list().isEmpty()) {
            user = (User) query.list().get(0);
        }
        transaction.commit();
        session.close();
        return user;
    }


    @Override
    public List<User> selectAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return allUsers;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(DELETE_USERS_HIBERNATE);
        query.setParameter("id", id);
        rowDeleted = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return rowDeleted;

    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }
}

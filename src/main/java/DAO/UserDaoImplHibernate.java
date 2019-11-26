package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImplHibernate implements UserDAO {

    private static final String SELECT_USER_BY_ID_HIBERNATE = "FROM User WHERE id = :id";
    private static final String DELETE_USERS_HIBERNATE = "DELETE FROM User WHERE id = :id";
    private Session session;

    public UserDaoImplHibernate(Session session) {
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
        User user;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(SELECT_USER_BY_ID_HIBERNATE);
        query.setParameter("id", id);
        user = (User) query.uniqueResult();
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

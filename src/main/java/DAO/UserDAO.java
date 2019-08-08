package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Session session;
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
            " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
    private static final String CREATE_TABLE_SQL = "create table if not exists users " +
            "(id bigint auto_increment,name varchar(256), email varchar(256), " +
            "country varchar(256), primary key (id));";
    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS users;";


    private static final String SELECT_USER_BY_ID_HIBERNATE = "FROM User WHERE id = :brand";
    private static final String DELETE_USERS_HIBERNATE = "DELETE FROM User WHERE id = :id";

    private static final Connection connection = UserService.getInstance().getConnection();

    public UserDAO() {

    }

    public UserDAO(Session session) {
        this.session = session;
    }

    public void createTable() {
        System.out.println(CREATE_TABLE_SQL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_SQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void dropTable() {
        System.out.println(DROP_TABLE_SQL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE_SQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void insertUser(User user) {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }


    public void insertUserH(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User selectUserH(int id) {
        User user;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(SELECT_USER_BY_ID_HIBERNATE);
        query.setParameter("id", id);
        user = (User) query.list().get(0);
        transaction.commit();
        session.close();
        return user;
    }

    public User selectUser(int id) {
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {

        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {

        }
        return users;
    }

    public List<User> selectAllUsersH() {
        Transaction transaction = session.beginTransaction();
        List<User> allCars = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return allCars;
    }

    public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {

        }
        return rowDeleted;
    }

    public boolean deleteUserH(int id) {
        boolean rowDeleted = false;
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(DELETE_USERS_HIBERNATE);
        query.setParameter("id", id);
        rowDeleted = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return rowDeleted;

    }

    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {

        }
        return rowUpdated;
    }

    public void updateUserH(User user) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

}

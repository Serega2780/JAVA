package dao;

import model.User;
import service.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplJDBC implements UserDAO {
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, password, role, email, country) VALUES " +
            " (?, ?, ?, ?,?);";

    private static final String SELECT_USER_BY_ID = "select id, name, password, role, email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String SELECT_NOT_ADMINS = "select * from users where role = ?;";
    private static final String SELECT_USER_BY_ROLE = "select * from users where name = ? AND password = ?";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,password = ?,role = ?,email= ?, country =? where id = ?;";
    private static final String CREATE_TABLE_SQL = "create table if not exists users " +
            "(id bigint auto_increment,name varchar(256), password varchar(256), role varchar(256),email varchar(256), " +
            "country varchar(256), primary key (id));";
    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS users;";

    private static final Connection connection = DBHelper.getInstance().getConnection();

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

    @Override
    public void insertUser(User user) {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, password, role, email, country);
            }
        } catch (SQLException e) {

        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, role, email, country));
            }
        } catch (SQLException e) {

        }
        return users;
    }

    @Override
    public List<User> selectNotAdmins() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOT_ADMINS)) {
            preparedStatement.setString(1, "user");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, role, email, country));
            }
        } catch (SQLException e) {

        }
        return users;
    }

    @Override
    public User selectUserByRole(String name, String password) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ROLE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("name");
                String uName = rs.getString("name");
                String uPassword = rs.getString("password");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, uName, uPassword, role, email, country);

            }
        } catch (SQLException e) {

        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {

        }
        return rowDeleted;
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getCountry());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {

        }

    }

}
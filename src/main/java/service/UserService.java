package service;

import DAO.UserDAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserService {
    /*Singleton*/
    private static UserService INSTANCE;
    private final Connection connection;
    private final UserDAO userDAO;

    private UserService() {

        this.connection = getMySQLConnection();
        this.userDAO = getUserDAO();
    }

    public static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }

        return INSTANCE;
    }

    private UserDAO getUserDAO() {
        UserDAO userDAO = null;
        if (this.userDAO == null) {
            userDAO = new UserDAO();
        }

        return userDAO;
    }

    public UserDAO getDAO() {
        return this.userDAO;
    }


    public Connection getConnection() {
        return this.connection;
    }

    private static Connection getMySQLConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("serverTimezone=GMT");          //login
            //   append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString(), "root", "root");
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }

    }


}

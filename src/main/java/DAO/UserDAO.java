package DAO;

import model.User;

import java.util.List;

public interface UserDAO {

    void insertUser(User user);

    User selectUser(int id);

    List<User> selectAllUsers();

    List<User> selectNotAdmins();

    User selectUserByRole(String name, String password);

    boolean deleteUser(int id);

    void updateUser(User user);
}

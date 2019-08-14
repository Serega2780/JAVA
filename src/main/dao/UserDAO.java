package main.dao;

import main.model.User;

import java.util.List;

public interface UserDAO {

    void insertUser(User user);

    User selectUser(int id);

    List<User> selectAllUsers();

    boolean deleteUser(int id);

    void updateUser(User user);
}

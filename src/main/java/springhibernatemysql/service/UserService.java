package springhibernatemysql.service;

import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    List<Role> getAllRoles();
/*
    User getUserById(int id);

    User createOrUpdateUser(User user);

    void deleteUser(int id);

    User selectUserByName(String name);

    List<User> selectUsersByRole(String role);
    */

}

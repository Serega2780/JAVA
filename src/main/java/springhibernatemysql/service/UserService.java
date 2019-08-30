package springhibernatemysql.service;

import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(int id);

    User getUserById(int id);

    List<User> selectUsersByRole();

}

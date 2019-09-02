package springhibernatemysql.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional

public interface UserDao {
    User getUserByName(String name);

    List<User> getUsers();

    User getUserById(int id);

    List<User> getUsersByRole(String role);

    void removeUser(int id);

    void addUser(User user);
}

package springhibernatemysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import springhibernatemysql.dao.RoleDao;
import springhibernatemysql.dao.UserDao;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final RoleDao roleDao;
    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;

    }

    @Override

    public List<User> getAllUsers() {
        List<User> users;
        users = userDao.getUsers();

        return users;
    }

    @Override
    public void createUser(User user) {

        userDao.addUser(user);


    }

    @Override
    public void deleteUser(int id) {
        userDao.removeUser(id);
    }


    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = this.userDao.getUserByName(userName);
        return user;

    }

    @Override
    public List<User> selectUsersByRole() {
       return userDao.getUsersByRole("");

    }

}

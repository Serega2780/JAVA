package springhibernatemysql.service;

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

    private final UserDao userDao;

    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;

    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> users;
        users = userDao.getUsers();
        for (User user : users) {
            user.setGrantedAuthorities(this.roleDao.getRolesById(user.getId()));
        }
        return users;
    }

    @Override
    @Transactional
    @ModelAttribute(name="allRoles")
    public List<Role> getAllRoles() {
        return roleDao.getRoles();
    }

    /*
        @Override
        @Transactional
        public User getUserById(int id) {
            if (userRepo.findById(id).isPresent())
                return userRepo.findById(id).get();
            return null;
        }

        @Override
        @Transactional
        public User createOrUpdateUser(User user) {
            return userRepo.save(user);
        }

        @Override
        @Transactional
        public void deleteUser(int id) {
            userRepo.deleteById(id);
        }

        @Override
        public User selectUserByName(String name) {
            for (User user : getAllUsers()) {
                if (user.getName().equals(name))

                    return user;
            }
            return null;
        }

        @Override
        public List<User> selectUsersByRole(String role) {
            return null;
                    /*getAllUsers().stream().filter(u -> u.getRole().equals(role.toLowerCase()))
                    .collect(Collectors.toList());



        }
    */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = this.userDao.getUserByName(userName);
        user.setGrantedAuthorities(this.roleDao.getRolesById(user.getId()));
        return user;
        //this.selectUserByName(userName);
/*
        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + user);
*/
        // [ROLE_USER, ROLE_ADMIN,..]
        //  List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

        // List<GrantedAuthority> grantList = new ArrayList<>();

        // GrantedAuthority authority = new SimpleGrantedAuthority(user.getAuthorities());
        // grantList.add(authority);

        //  System.out.println(authority.getAuthority());



/*
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getEncrytedPassword(), grantList);
*/

                /*new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), grantList);
*/

    }

}

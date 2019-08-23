package springhibernatemysql.service;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springhibernatemysql.domain.User;
import springhibernatemysql.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        userRepo.findAll().forEach(user::add);
        return user;
    }

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
        return getAllUsers().stream().filter(u -> u.getRole().equals(role.toLowerCase()))
                .collect(Collectors.toList());

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.selectUserByName(userName);

        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + user);

        // [ROLE_USER, ROLE_ADMIN,..]
        //  List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toUpperCase());
        grantList.add(authority);

        System.out.println(authority.getAuthority());

/*
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getEncrytedPassword(), grantList);
*/
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), grantList);
    }
}

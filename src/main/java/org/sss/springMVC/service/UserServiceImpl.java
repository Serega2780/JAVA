package org.sss.springMVC.service;

import org.springframework.ui.Model;
import org.sss.springMVC.domain.User;
import org.sss.springMVC.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public List<User> getAllUsers(UserRepository userRepo) {
        List<User> user = new ArrayList<>();
        userRepo.findAll().forEach(user::add);
        return user;
    }

    public User getUserById(int id, UserRepository userRepo) {
        if (userRepo.findById(id).isPresent())
            return userRepo.findById(id).get();
        return null;
    }

    public User createOrUpdateUser(User user, UserRepository userRepo) {
        return userRepo.save(user);
    }

    public void deleteUser(int id, UserRepository userRepo) {
        userRepo.deleteById(id);
    }
}

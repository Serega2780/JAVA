package org.sss.springMVC.service;

import org.sss.springMVC.domain.User;
import org.sss.springMVC.repository.UserRepository;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers(UserRepository userRepo);

    public User getUserById(int id, UserRepository userRepo);

    public User createOrUpdateUser(User user, UserRepository userRepo);

    public void deleteUser(int id, UserRepository userRepo);
}

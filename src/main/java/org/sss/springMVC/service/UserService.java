package org.sss.springMVC.service;

import org.sss.springMVC.domain.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(int id);

    public User createOrUpdateUser(User user);

    public void deleteUser(int id);
}

package org.sss.springMVC.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.sss.springMVC.domain.User;
import org.sss.springMVC.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
}

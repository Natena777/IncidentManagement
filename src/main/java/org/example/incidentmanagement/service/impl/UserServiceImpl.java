package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.model.User;
import org.example.incidentmanagement.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {


    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public void updateUser(User user) {

    }
}

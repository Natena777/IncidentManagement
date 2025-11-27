package org.example.incidentmanagement.service;

import org.example.incidentmanagement.model.User;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserById(int id);

    List<User> findAllUsers();

    void deleteUser(int id);

    void updateUser(User user);


}

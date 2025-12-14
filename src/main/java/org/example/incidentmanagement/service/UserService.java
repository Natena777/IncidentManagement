package org.example.incidentmanagement.service;

import org.example.incidentmanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    User findUserById(int id);

    List<User> findAllUsers();

    void deleteUser(int id);

    void updateUser(User user);


}

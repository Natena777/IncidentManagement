package org.example.incidentmanagement.repository;


import org.example.incidentmanagement.model.User;

import java.util.List;

public interface UserRepository {

    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findById(int id);
    public List<User> findAll();
    public void save(User user);
    public void deleteById(int id);
}

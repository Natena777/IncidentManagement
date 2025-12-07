package org.example.incidentmanagement.repository;


import org.example.incidentmanagement.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<Integer, User> storage = new HashMap<>();
    private int currentId = 1;

    @Override
    public User findByUsername(String username) {
        return storage.values()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return storage.values()
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findById(int id) {
        return storage.get(id);
    }

    @Override
    public List<User> findAll() {
        var result = new ArrayList<User>();
        result.addAll(storage.values());
        return result;
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(currentId++);
        }
        storage.put(user.getId(), user);
    }

    @Override
    public void deleteById(int id) {
        storage.remove(id);
    }

    @Override
    public void registrationUser(User user) {
        if (user.getId() == 0) {
            user.setId(currentId++);
        }
        storage.put(user.getId(), user);
    }

}

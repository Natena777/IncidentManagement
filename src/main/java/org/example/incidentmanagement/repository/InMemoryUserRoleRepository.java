package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.UserRoles;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserRoleRepository implements UserRolesRepository{

    private final Map<Integer, UserRoles> database = new HashMap<>();
    private int currentId = 1;

    @Override
    public List<UserRoles> findAll() {
        var result = new ArrayList<UserRoles>();
        result.addAll(database.values());
        return result;
    }


    @Override
    public List<UserRoles> findByUserId(int userId) {
        var result = new ArrayList<UserRoles>();
        result.addAll(database.values());
        return result;
    }

    @Override
    public List<UserRoles> findByRoleId(int roleID) {
        var result = new ArrayList<UserRoles>();
        result.addAll(database.values());
        return result;
    }


    @Override
    public UserRoles findById(int id) {
        return database.get(id);
    }

    @Override
    public void save(UserRoles userRoles) {
        if (userRoles.getId() == 0) {
            userRoles.setId(currentId++);
        }
        database.put(userRoles.getId(), userRoles);
    }

    @Override
    public void delete(UserRoles userRoles) {
        database.remove(userRoles.getId());
    }

    @Override
    public void update(UserRoles userRoles) {
        database.put(userRoles.getId(), userRoles);

    }
}

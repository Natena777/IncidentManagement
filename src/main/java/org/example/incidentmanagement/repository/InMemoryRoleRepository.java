package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryRoleRepository implements RoleRepository {

    private final Map<Integer, Role> database = new HashMap<>();
    private int currentId = 1;

    @Override
    public Role findByName(String name) {
        return database.values()
                .stream()
                .filter(role -> role.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Role> findAll() {
        var result = new ArrayList<Role>();
        result.addAll(database.values());
        return result;
    }

    @Override
    public void create(Role role) {
        if (role.getId() == 0){
            role.setId(currentId++);
        }
        database.put(role.getId(), role);
    }

    @Override
    public void update(int id, Role role) {
        database.put(id, role);
    }

    @Override
    public void delete(Role role) {
        database.remove(role.getId());

    }
}

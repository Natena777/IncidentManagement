package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRoleRepository {

    private final Map<Integer, User> database = new HashMap<>();
    private int currentId = 1;
}

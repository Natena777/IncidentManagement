package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByName(String name);

}

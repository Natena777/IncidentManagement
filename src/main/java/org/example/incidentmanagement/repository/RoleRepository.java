package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByName(String name);

    @Query("select r.name from Role r where r.id = :id")
    Optional<String> findRoleNameById(Integer id);

}

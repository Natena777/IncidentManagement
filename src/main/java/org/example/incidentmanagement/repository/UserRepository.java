package org.example.incidentmanagement.repository;


import org.example.incidentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query("select su.fullName from User su where su.id = :id")
    Optional<String> findFullNameById(Integer id);
}

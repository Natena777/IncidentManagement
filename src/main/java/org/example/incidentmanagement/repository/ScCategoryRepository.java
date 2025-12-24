package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.ScCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScCategoryRepository extends JpaRepository<ScCategory, Integer> {

}

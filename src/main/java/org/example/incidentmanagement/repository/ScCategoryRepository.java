package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.ScCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ScCategoryRepository extends JpaRepository<ScCategory, Integer> {

    Optional<ScCategory> findByScCategoryName(String categoryName);

    @Query("select sc.scCategoryName from ScCategory sc where sc.id = :scCategoryId")
    String findByScCategoryNameById(Integer scCategoryId);

}

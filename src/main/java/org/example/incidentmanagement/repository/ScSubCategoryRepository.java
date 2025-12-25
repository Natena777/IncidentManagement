package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.ScSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScSubCategoryRepository extends JpaRepository<ScSubCategory, Integer> {

    Optional<ScSubCategory> findByScSubCategoryName(String subCategoryName);


    @Query("select ssb.scSubCategoryName from ScSubCategory ssb where ssb.id = :scSubCategoryId")
    String findScSubCategoryNameById(Integer scSubCategoryId);

}

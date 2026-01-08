package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.dto.response.ServiceCatalogFullResponseDto;
import org.example.incidentmanagement.entity.ScServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScServicesRepository extends JpaRepository<ScServices, Integer> {


    Optional<ScServices> findByServicesName(String name);

    @Query("select ss.servicesName from ScServices ss where ss.id = :id")
    String findServiceNameById(Integer id);

    boolean existsByAssigneeGroupId(Integer id);

    @Query("""
    select new org.example.incidentmanagement.dto.response.ServiceCatalogFullResponseDto(
        ss.id,
        sd.departmentName || ' -> ' ||
        sc.scCategoryName || ' -> ' ||
        ssc.scSubCategoryName || ' -> ' ||
        ss.servicesName
    )
    from ScServices ss
    join ScDepartments sd on ss.scDepartmentId = sd.id
    join ScCategory sc on ss.scCategoryId = sc.id
    join ScSubCategory ssc on ss.scSubCategoryId = ssc.id
""")
    List<ServiceCatalogFullResponseDto> findFullFlowServiceCatalog();

}

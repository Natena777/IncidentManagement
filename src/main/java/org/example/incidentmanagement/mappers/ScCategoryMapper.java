package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.createRequest.CrScCategoryRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrScCategoryResponseDto;
import org.example.incidentmanagement.dto.response.ScCategoryResponseDto;
import org.example.incidentmanagement.entity.ScCategory;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface ScCategoryMapper {

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "scDepartmentId", target = "scDepartment", qualifiedByName = "ScDepartmentIdToName")
    ScCategoryResponseDto toScCategoryResponseDto(ScCategory scCategory);

    
    @Mapping(source = "scDepartmentId", target = "scDepartment", qualifiedByName = "ScDepartmentIdToName")
    CrScCategoryResponseDto toCreateScCategoryResponseDto(ScCategory scCategory);
   

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "A")
    ScCategory toScCategoryEntity(CrScCategoryRequestDto crScCategoryRequestDto);
    default ScCategory toScCategoryEntityDefaults(CrScCategoryRequestDto crScCategoryRequestDto, Integer currentUserId){
        ScCategory entity = toScCategoryEntity(crScCategoryRequestDto);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setCreatedBy(currentUserId);
        return entity;
    }

    

}

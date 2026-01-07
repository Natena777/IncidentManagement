package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.createRequest.CrScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrScSubCategoryResponseDto;
import org.example.incidentmanagement.dto.response.ScSubCategoryResponseDto;
import org.example.incidentmanagement.entity.ScSubCategory;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface ScSubCategoryMapper {

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName =  "userIdToFullName")
    @Mapping(source = "scCategoryId", target = "scCategoryName", qualifiedByName = "ScCategoryIdToName")
    ScSubCategoryResponseDto toScSubCategoryResponseDto(ScSubCategory scSubCategory);

    @Mapping(source = "scCategoryId", target = "scCategoryName", qualifiedByName = "ScCategoryIdToName")
    CrScSubCategoryResponseDto toCreateScSubCategoryResponseDto(ScSubCategory scSubCategory);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "A")
    ScSubCategory toScSubCategory(CrScSubCategoryRequestDto createScSubCategory);
        default ScSubCategory toScSubCategoryEntityDefaults(CrScSubCategoryRequestDto createScSubCategory, Integer currentUserId){
        ScSubCategory entity = toScSubCategory(createScSubCategory);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setCreatedBy(currentUserId);
        return entity;
    }

}

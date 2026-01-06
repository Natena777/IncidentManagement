package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.createRequest.CreateScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateScSubCategoryResponseDto;
import org.example.incidentmanagement.dto.response.ScSubCategoryResponseDto;
import org.example.incidentmanagement.entity.ScSubCategory;
import org.example.incidentmanagement.service.DefaultConverter;
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
    CreateScSubCategoryResponseDto toCreateScSubCategoryResponseDto(ScSubCategory scSubCategory);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "A")
    ScSubCategory toScSubCategory(CreateScSubCategoryRequestDto createScSubCategory);
        default ScSubCategory toScSubCategoryEntityDefaults(CreateScSubCategoryRequestDto createScSubCategory, Integer currentUserId){
        ScSubCategory entity = toScSubCategory(createScSubCategory);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setCreatedBy(currentUserId);
        return entity;
    }

}

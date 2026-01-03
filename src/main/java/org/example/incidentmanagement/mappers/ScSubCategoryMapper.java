package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.requests.CreateScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.response.CreateScSubCategoryResponseDto;
import org.example.incidentmanagement.dto.response.ScSubCategoryResponseDto;
import org.example.incidentmanagement.entity.ScSubCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScSubCategoryMapper {


    ScSubCategoryResponseDto toScSubCategoryResponseDto(ScSubCategory scSubCategory);

    CreateScSubCategoryResponseDto toCreateScSubCategoryResponseDto(ScSubCategory scSubCategory);

    ScSubCategory toScSubCategory(CreateScSubCategoryRequestDto createScSubCategory);

        default ScSubCategory toScSubCategoryEntityDefaults(CreateScSubCategoryRequestDto createScSubCategory, Integer currentUserId){
        ScSubCategory entity = toScSubCategory(createScSubCategory);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setActive("A");
        entity.setCreatedBy(currentUserId);
        return entity;
    }

}

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


}

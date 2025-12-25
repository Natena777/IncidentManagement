package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateScCategoryRequestDto;
import org.example.incidentmanagement.dto.response.CreateScCategoryResponseDto;
import org.example.incidentmanagement.dto.response.ScCategoryResponseDto;
import org.example.incidentmanagement.entity.ScCategory;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ScCategoryMapper {

    ScCategoryResponseDto toScCategoryResponseDto(ScCategory scCategory);

    CreateScCategoryResponseDto toCreateScCategoryResponseDto(ScCategory scCategory);
   
    ScCategory toScCategoryEntity(CreateScCategoryRequestDto createScCategoryRequestDto);

    


}

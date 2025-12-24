package org.example.incidentmanagement.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScCategoryMapper {

    ScCategoryResponseDto toScCategoryResponseDto(ScCategory scCategory);

    CreateScCategoryResponseDto toCreateScCategoryResponseDto(ScCategory scCategory);
   
    ScCategory toScCategoryEntity(CreateScCategoryRequestDto createScCategoryRequestDto);

    


}

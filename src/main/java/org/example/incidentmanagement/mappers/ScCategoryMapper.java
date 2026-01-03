package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateScCategoryRequestDto;
import org.example.incidentmanagement.dto.response.CreateScCategoryResponseDto;
import org.example.incidentmanagement.dto.response.ScCategoryResponseDto;
import org.example.incidentmanagement.entity.ScCategory;
import org.mapstruct.Mapper;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Mapper(componentModel = "spring")
public interface ScCategoryMapper {

    ScCategoryResponseDto toScCategoryResponseDto(ScCategory scCategory);

    CreateScCategoryResponseDto toCreateScCategoryResponseDto(ScCategory scCategory);
   
    ScCategory toScCategoryEntity(CreateScCategoryRequestDto createScCategoryRequestDto);

    default ScCategory toScCategoryEntityDefaults(CreateScCategoryRequestDto createScCategoryRequestDto,  Integer currentUserId){
        ScCategory entity = toScCategoryEntity(createScCategoryRequestDto);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setActive("A");
        entity.setCreatedBy(currentUserId);
        return entity;
    }

    

}

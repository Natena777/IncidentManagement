package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateScCategoryRequestDto;
import org.example.incidentmanagement.dto.response.CreateScCategoryResponseDto;
import org.example.incidentmanagement.dto.response.ScCategoryResponseDto;
import org.example.incidentmanagement.entity.ScCategory;
import org.example.incidentmanagement.service.DefaultConverter;
import org.mapstruct.Mapper;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface ScCategoryMapper {

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    ScCategoryResponseDto toScCategoryResponseDto(ScCategory scCategory);

    
    CreateScCategoryResponseDto toCreateScCategoryResponseDto(ScCategory scCategory);
   

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "A")
    ScCategory toScCategoryEntity(CreateScCategoryRequestDto createScCategoryRequestDto);
    default ScCategory toScCategoryEntityDefaults(CreateScCategoryRequestDto createScCategoryRequestDto,  Integer currentUserId){
        ScCategory entity = toScCategoryEntity(createScCategoryRequestDto);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setCreatedBy(currentUserId);
        return entity;
    }

    

}

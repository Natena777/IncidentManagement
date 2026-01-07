package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.createRequest.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrScServicesResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateScServiceReqDto;
import org.example.incidentmanagement.dto.response.ScServicesResponseDto;
import org.example.incidentmanagement.entity.ScServices;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Mapper(componentModel = "spring",
        uses = {DefaultConverter.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScServicesMapper {

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName") 
    @Mapping(source = "assigneeGroupId", target = "assigneeGroupName", qualifiedByName = "assigneeGroupIdToGroupName") 
    @Mapping(source = "scDepartmentId", target = "scDepartmentName", qualifiedByName = "ScDepartmentIdToName")
    @Mapping(source = "scCategoryId", target = "scCategoryName", qualifiedByName = "ScCategoryIdToName")
    @Mapping(source = "scSubCategoryId", target = "scSubCategoryName", qualifiedByName = "ScSubCategoryIdToName")
    @Mapping(target = "responseTime", ignore = true)
    @Mapping(target = "resolutionTime", ignore = true)
    ScServicesResponseDto toScServicesResponseDto(ScServices scServices);


    @Mapping(source = "assigneeGroupId", target = "assigneeGroupName", qualifiedByName = "assigneeGroupIdToGroupName")
    @Mapping(source = "scDepartmentId", target = "scDepartmentName", qualifiedByName = "ScDepartmentIdToName")
    @Mapping(source = "scCategoryId", target = "scCategoryName", qualifiedByName = "ScCategoryIdToName")
    @Mapping(source = "scSubCategoryId", target = "scSubCategoryName", qualifiedByName = "ScSubCategoryIdToName")
    @Mapping(source = "id", target = "serviceId")
    @Mapping(target = "responseTime", ignore = true)
    @Mapping(target = "resolutionTime", ignore = true)
    CrScServicesResponseDto toCreateScServicesResponseDto (ScServices scServices);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    ScServices toScServicesEntity(CreateScServicesRequestDto createScServicesRequestDto);
        default ScServices toScServicesEntityDefaults(CreateScServicesRequestDto createScServicesRequestDto, Integer currentUserId){
            ScServices entity = toScServicesEntity(createScServicesRequestDto);
            entity.setCreatedDate(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setCreatedBy(currentUserId);
            return entity;
    }



    void toUpdateScServiceEntity(UpdateScServiceReqDto dto, @MappingTarget ScServices scServices);




}

package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.requests.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.response.CreateScServicesResponseDto;
import org.example.incidentmanagement.dto.response.ScServicesResponseDto;
import org.example.incidentmanagement.entity.ScServices;
import org.example.incidentmanagement.service.DefaultConverter;
import org.mapstruct.Mapper;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface ScServicesMapper {

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName") 
    @Mapping(source = "assigneeGroupId", target = "assigneeGroup", qualifiedByName = "assigneeGroupIdToGroupName") 
    ScServicesResponseDto toScServicesResponseDto(ScServices scServices);

    @Mapping(source = "assigneeGroupId", target = "assigneeGroup", qualifiedByName = "assigneeGroupIdToGroupName") 
    CreateScServicesResponseDto toCreateScServicesResponseDto (ScServices scServices);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "A")
    ScServices toScServicesEntity(CreateScServicesRequestDto createScServicesRequestDto);
        default ScServices toScServicesEntityDefaults(CreateScServicesRequestDto createScServicesRequestDto, Integer currentUserId){
            ScServices entity = toScServicesEntity(createScServicesRequestDto);
            entity.setCreatedDate(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setCreatedBy(currentUserId);
            return entity;
    }


}

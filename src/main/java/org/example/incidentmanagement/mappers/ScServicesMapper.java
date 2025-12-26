package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.requests.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.response.CreateScServicesResponseDto;
import org.example.incidentmanagement.dto.response.ScServicesResponseDto;
import org.example.incidentmanagement.entity.ScServices;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScServicesMapper {

    ScServicesResponseDto toScServicesResponseDto(ScServices scServices);

    CreateScServicesResponseDto toCreateScServicesResponseDto (ScServices scServices);

    ScServices toScServicesEntity(CreateScServicesRequestDto createScServicesRequestDto);



}

package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.enums.RequestChannelEnums;
import org.example.incidentmanagement.enums.RequestTimeUnitEnums;
import org.springframework.web.bind.annotation.*;
import org.example.incidentmanagement.enums.RequestTypeEnums;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/enums")
@Tag(name = "Enum Endpoints",
    description = "Get Enum Lists")
public class EnumController {

    @GetMapping("/request-types")
    @Operation(description = "Get Service Catalog Type")
    public RequestTypeEnums[] getRequestTypes() {
        return RequestTypeEnums.values();
    }

    @GetMapping("/request-timeUnits")
    @Operation(description = "Get Service Working Time Unit")
    public List<String> getRequestTimeUnits() {
        return Arrays.stream(RequestTimeUnitEnums.values())
                .map(RequestTimeUnitEnums::getDisplayValue)
                .toList();
    }


    @GetMapping("/request-channels")
    @Operation(description = "Get Request Channels")
    public RequestChannelEnums[] getRequestChannels() {
        return RequestChannelEnums.values();
    }

}

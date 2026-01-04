package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.example.incidentmanagement.enums.RequestTypeEnums;

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

}

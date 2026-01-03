package org.example.incidentmanagement.controller;

import org.springframework.web.bind.annotation.*;
import org.example.incidentmanagement.enums.RequestTypeEnums;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/request-types")
    public RequestTypeEnums[] getRequestTypes() {
        return RequestTypeEnums.values();
    }

}

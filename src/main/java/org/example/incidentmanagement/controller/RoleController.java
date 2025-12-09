package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.entity.Role;
import org.example.incidentmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Tag(name = "Role Controller API",
        description = "Role Management Controllers")
public class RoleController {
    @Autowired
    RoleService roleService;


    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }


    @PostMapping("/create")
    public Role createRole(@RequestBody Role role) {
        return roleService.create(role);
    }


}

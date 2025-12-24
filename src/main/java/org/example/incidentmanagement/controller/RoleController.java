package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.RoleRequestDto;
import org.example.incidentmanagement.dto.response.RoleResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateRoleRequestDto;
import org.example.incidentmanagement.entity.Role;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@Tag(name = "Role Controller API",
        description = "Role Management Controllers")
public class RoleController {

    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }


    @GetMapping("/{name}")
    public RoleResponseDto findByName(@PathVariable String name) {
        return roleService.findByRoleName(name);
    }

    @PostMapping("/create")
    public RoleResponseDto createRole(@RequestBody RoleRequestDto roleRequestDto) {
        return roleService.createRole(roleRequestDto);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Object> deleteRole(@PathVariable String name) {
        roleService.deleteRole(name);
        ApiResponse response = new ApiResponse(
                ResponseCodes.ROLE_DELETED.getCode(),
                ResponseCodes.ROLE_DELETED.getMessage()
        );

        return ResponseEntity.ok(response);
    }


    @PutMapping("update/{name}")
    public RoleResponseDto updateRole(@PathVariable String name,
                                      @RequestBody UpdateRoleRequestDto updateRoleRequestDto) {
        return roleService.updateRole(name, updateRoleRequestDto);
    }






}

package org.example.incidentmanagement.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/userRole")
@Tag(name = "User Role API",
        description = "User Role Management Controllers")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;


    @GetMapping
    public List<UserRoleResponseDto> findUserRoles() {
        return userRoleService.findAllUserRoles();
    }

    @GetMapping("/{userID}")
    public List<UserRoleResponseDto> findUserRolesByUserId(int userId) {
        return userRoleService.findUserRolesByUserID(userId);
    }


    @GetMapping("/roleID")
    public List<UserRoleResponseDto> findUsersRoleByRoleID(int roleID) {
        return userRoleService.findUsersRoleByRoleId(roleID);
    }

    @PostMapping("/create")
    public UserRoleResponseDto createUserRole(@RequestBody CreateUserRoleRequestDto crrequestDto) {
        return userRoleService.createUserRole(crrequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserRole(@PathVariable int id) {
        userRoleService.deleteUserRole(id);
    }










}

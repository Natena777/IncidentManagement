package org.example.incidentmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/userRole")
@Tag(name = "User Role API",
        description = "User Role Management Controllers")
public class UserRoleController {


    private final UserRoleService userRoleService;
    public UserRoleController( UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    @Operation(summary = "Find All System User Roles")
    public List<UserRoleResponseDto> findUserRoles() {
        return userRoleService.findAllUserRoles();
    }

    @GetMapping("/{userId}/roles")
    @Operation(summary = "Find User All Roles By UserID")
    public List<UserRoleResponseDto> findUserRolesByUserId(@PathVariable int userId) {
        return userRoleService.findAllUserRolesByUserID(userId);
    }

    @GetMapping("/{roleID}/users")
    @Operation(summary = "Find Users In Role By RoleID")
    public List<UserRoleResponseDto> findUsersRoleByRoleID(@PathVariable int roleID) {
        return userRoleService.findUsersRoleByRoleId(roleID);
    }

    @PostMapping("/create")
    @Operation(summary = "Add New Role On User")
    public UserRoleResponseDto createUserRole(@Valid @RequestBody CreateUserRoleRequestDto crrequestDto) {
        return userRoleService.createUserRole(crrequestDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Role From User")
    public ResponseEntity<ApiResponse> deleteUserRole(@PathVariable int id) {
        userRoleService.deleteUserRole(id);

        ApiResponse response = new ApiResponse(
                ResponseCodes.USER_DELETED.getCode(),
                ResponseCodes.USER_DELETED.getMessage()
        );

        return ResponseEntity.ok(response);

    }


    @GetMapping("/getID")
    public Integer findUserRoleId (@RequestParam("p_user") Integer p_user,
                                   @RequestParam("p_role") Integer p_role){
        return userRoleService.findUserRoleId(p_user, p_role);
    } 

}

package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller API",
        description = "User Management Controllers")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get All System Users")
    public List<UserResponseDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Find System User By ID")
    public UserResponseDto findById(@PathVariable int id) {
        UserResponseDto user = userService.findUserById(id);
        return user;
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Find System User By Email")
    public UserResponseDto findByEmail(@PathVariable String email) {
        UserResponseDto user = userService.findUserByEmail(email);
        return user;
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete System User By ID")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {

        userService.deleteUser(id);

        ApiResponse response = new ApiResponse(
                ResponseCodes.USER_DELETED.getCode(),
                ResponseCodes.USER_DELETED.getMessage()
        );

        return ResponseEntity.ok(response);
    }


}
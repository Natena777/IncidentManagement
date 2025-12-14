package org.example.incidentmanagement.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.requests.LoginUserDto;
import org.example.incidentmanagement.dto.requests.RegistrationUserDto;
import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.mappers.UserMapper;
import org.example.incidentmanagement.service.LoginService;
import org.example.incidentmanagement.service.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication API",
    description = "Registration And Login Endpoints")
public class AuthController {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    public AuthController(RegistrationService registrationService, LoginService loginService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
    }


    @PostMapping("/registration")
    public UserResponseDto createUser(@RequestBody RegistrationUserDto registruserdto) {
        User user = new User();
        user.setFirstname(registruserdto.getFirstname());
        user.setLastname(registruserdto.getLastname());
        user.setEmail(registruserdto.getEmail());
        user.setAddress(registruserdto.getAddress());
        user.setPassword(registruserdto.getPassword());
        registrationService.registerUser(user);
        return UserMapper.toResponse(user);
    }


    @PostMapping("/login")
    public User login(@RequestBody LoginUserDto loginuserdto) {
        User user = new User();
        user.setUsername(loginuserdto.getUsername());
        user.setPassword(loginuserdto.getPassword());
        loginService.login(user.getUsername(), user.getPassword());
        return user;
    }


}

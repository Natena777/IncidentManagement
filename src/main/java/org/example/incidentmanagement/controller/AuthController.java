package org.example.incidentmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.requests.LoginUserDto;
import org.example.incidentmanagement.dto.requests.RegistrationUserDto;
import org.example.incidentmanagement.dto.response.LoginResponseDto;
import org.example.incidentmanagement.dto.response.RegistrationUserRespDto;
import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.security.CustomUserPrincipal;
import org.example.incidentmanagement.security.JwtUtil;
import org.example.incidentmanagement.service.interfaces.LoginService;
import org.example.incidentmanagement.service.interfaces.RegistrationService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API",
    description = "Registration And Login Endpoints")
public class AuthController {

    private final RegistrationService registrationService;
    private final LoginService loginService;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    public AuthController(RegistrationService registrationService, LoginService loginService,
                          JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/registration")
    @Operation(summary = "Create New User", security = {})
    public RegistrationUserRespDto createUser(@RequestBody RegistrationUserDto registruserdto) {
        RegistrationUserRespDto regUser =  registrationService.registerUser(registruserdto);
        return regUser;
    }


    @PostMapping("/login")
    @Operation(summary = "Login User", security = {})
    public LoginResponseDto login(@RequestBody LoginUserDto loginuserdto) {
        User user = loginService.login(loginuserdto.getUsername(), loginuserdto.getPassword());

        CustomUserPrincipal principal = (CustomUserPrincipal) userDetailsService.loadUserByUsername(user.getUsername());

        //ტოკენის გენერაცია
        String token = jwtUtil.generateToken(principal);
        return new LoginResponseDto(token);
    }


}

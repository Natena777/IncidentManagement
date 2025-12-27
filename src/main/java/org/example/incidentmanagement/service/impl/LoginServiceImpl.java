package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.repository.UserRepository;
import org.example.incidentmanagement.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public LoginServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {


        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.info("Invalid username: {}", username);
            throw new CustomException(ResponseCodes.INVALID_USERNAME);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            logger.info("Invalid password for user: {}", username);
            throw new CustomException(ResponseCodes.INVALID_PASSWORD);
        }

        logger.info("User {} logged in successfully", username);
        return user;
    }

}


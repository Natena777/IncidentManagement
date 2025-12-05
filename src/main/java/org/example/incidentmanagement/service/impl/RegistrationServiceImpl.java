package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ErrorCodes;
import org.example.incidentmanagement.repository.UserRepository;
import org.example.incidentmanagement.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private final UserRepository userRepository;
    public RegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public User registerUser(User user) {
        if (!user.getEmail().contains("@")){
            throw new CustomException(ErrorCodes.INVALID_EMAIL);
        }
        user.setUsername(extractUsername(user.getEmail()));
        user.setStartDate(LocalDateTime.now());
        userRepository.registrationUser(user);
        return user;

    }

    public String extractUsername(String email) {
        String username = "";
        username = email.split("@")[0];
        logger.info("Generated Username: " +  username);
        return username;
    }
}

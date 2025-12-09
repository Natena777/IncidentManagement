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
        //Generate Username and Start Date
        user.setUsername(extractUsername(user.getEmail()));
        user.setStartDate(LocalDateTime.now());

        //Email Check
        if (user.getEmail().length() > 0) {
            logger.info("Called Check Email For: " + user.getEmail());
            if (!user.getEmail().contains("@")){
                logger.info("Email {} Does Not Contain @ ",  user.getEmail());
                throw new CustomException(ErrorCodes.INVALID_EMAIL);
            }

            if (userRepository.findByEmail(user.getEmail()) != null){
                logger.info("Email {} Already Exists", user.getEmail());
                throw new CustomException(ErrorCodes.INVALID_EMAIL);
            }

        }



        //Password Length Check
        if (user.getPassword().length() > 0 ) {
            logger.info("Called Check Password For User : " + user.getUsername());
            if (user.getPassword().length() < 8) {
                throw new CustomException(ErrorCodes.INVALID_PASSWORD);
            }

        }

        //Set User Info
        userRepository.registrationUser(user);
        return user;

    }

    //Generate Username From Email
    public String extractUsername(String email) {
        String username = "";
        username = email.split("@")[0];
        logger.info("Called Generated Username: " +  username);
        return username;
    }
}

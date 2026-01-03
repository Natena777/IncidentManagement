package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.RegistrationUserDto;
import org.example.incidentmanagement.dto.response.RegistrationUserRespDto;
import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.UserMapper;
import org.example.incidentmanagement.repository.UserRepository;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private CurrentUserService currentUserService;

    public RegistrationServiceImpl(UserRepository userRepository, UserMapper userMapper,
                                   PasswordEncoder passwordEncoder, CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUserService = currentUserService;
    }



    @Override
    public RegistrationUserRespDto registerUser(RegistrationUserDto userDto) {
        //Email Check
        if (userDto.getEmail().length() > 0) {
            logger.info("Called Check Email For: {}", userDto.getEmail());
            if (!userDto.getEmail().contains("@")){
                logger.info("Email {} Does Not Contain @ ",  userDto.getEmail());
                throw new CustomException(ResponseCodes.INVALID_EMAIL);
            }

            if (userRepository.findByEmail(userDto.getEmail()) != null){
                logger.info("Email {} Already Exists", userDto.getEmail());
                throw new CustomException(ResponseCodes.INVALID_EMAIL);
            }

        }

        //Password Length Check
        if (userDto.getPassword().length() > 0 ) {
            logger.info("Called Check Password For Client : {} {}", userDto.getFirstName(), userDto.getLastName());
            if (userDto.getPassword().length() < 8) {
                throw new CustomException(ResponseCodes.INVALID_PASSWORD);
            }

        }

        String userName = extractUsername(userDto.getEmail());
        String fullName = userDto.getFirstName() + " " + userDto.getLastName();
        String password = passwordEncoder.encode(userDto.getPassword());
            
        User user = userMapper.toEntityDetails(userDto, userName, fullName, password);
        User savedUser = userRepository.save(user);

        return userMapper.toRegistrationResp(savedUser);

    }

    //Generate Username From Email
    public String extractUsername(String email) {
        String username = email.split("@")[0];
        logger.info("Called Generated Username: {}",  username);
        return username;
    }


}

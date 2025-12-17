package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.repository.UserRepository;
import org.example.incidentmanagement.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    private final UserRepository userRepository;
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {

        User user = userRepository.findByUsername(username);

//        //Check Username
//        if (user.isEmpty() || !user.get().getUsername().equals(username)) {
//            logger.info(user.get().getUsername());
//            throw new CustomException(ResponseCodes.INVALID_USERNAME);
//        }
//        //Check Password
//        if (!user.get().getPassword().equals(password)) {
//            throw new CustomException(ResponseCodes.INVALID_PASSWORD);
//        }

        logger.info("Login attempt");
        return null;

    }
}

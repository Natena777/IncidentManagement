package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.model.User;
import org.example.incidentmanagement.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegistrationServiceImpl implements RegistrationService {

    Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Override
    public User registerUser(User user) {
        logger.info("Register user");
        return null;
    }
}

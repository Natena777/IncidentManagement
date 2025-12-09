package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ErrorCodes;
import org.example.incidentmanagement.repository.UserRepository;
import org.example.incidentmanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger  = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findUserByUsername(String username) {
        logger.info("Called findUserByUsername: " + username);
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        logger.info("Called findUserByEmail: " + email);
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(int id) {
        logger.info("Called findUserById: " + id);
        User user = userRepository.findById(id);

        if (user == null) {
            throw new CustomException(ErrorCodes.INVALID_USER);
        }

        return user;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        logger.info("Called findAllUsers" );
        return users;
    }

    @Override
    public void deleteUser(int id) {
        logger.info("Called deleteUser: " + findUserById(id).getUsername());
        userRepository.deleteById(id);

    }

    @Override
    public void updateUser(User user) {
        logger.info("Called updateUser: " + user);
        userRepository.save(user);

    }
}

package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.UserMapper;
import org.example.incidentmanagement.repository.UserRepository;
import org.example.incidentmanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger  = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponseDto findUserByUsername(String username) {
        logger.info("Called findUserByUsername: {}",  username);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            logger.info("User with Name {} not found", username);
            throw new CustomException(ResponseCodes.INVALID_USER);
        }

        return userMapper.toResponseDto(user);

    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        logger.info("Called findUserByEmail: {}", email);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            logger.info("User with email {} not found", email);
            throw new CustomException(ResponseCodes.INVALID_USER);
        }

        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto findUserById(int id) {
        logger.info("Called findUserById: {}", id);
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            logger.info("User with {} not found", id);
            throw new CustomException(ResponseCodes.INVALID_USER);
        }
        logger.info("User with id {} found", id);
        return userMapper.toResponseDto(user.orElse(null));

    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        logger.info("Called findAllUsers");
        List<User> users = userRepository.findAll();
        return userMapper.toResponseDtoList(users);
    }

    @Override
    public void deleteUser(int id) {
        logger.info("Called deleteUser: {}",  findUserById(id));
        userRepository.deleteById(id);

    }

    @Override
    public void updateUser(User user) {
        logger.info("Called updateUser: {}", user);
        userRepository.save(user);

    }

    @Override
    public Boolean existsUser(int id) {
        Boolean exists = userRepository.existsById(id);
        logger.info("Called Check User Exists on ID '{}' , Result: '{}'", id, exists  );
        return exists;
    }

    @Override
    public String getFullName(Integer id) {
        String userFullName = userRepository.findFullNameById(id).orElse(null);
        logger.info("Called GetFullName on ID {}  Result: {}", id, userFullName);
        return userFullName;
    }
}

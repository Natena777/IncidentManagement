package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.entity.UserRoles;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.UserRoleMapper;
import org.example.incidentmanagement.repository.UserRolesRepository;
import org.example.incidentmanagement.service.RoleService;
import org.example.incidentmanagement.service.UserRoleService;
import org.example.incidentmanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    private UserRolesRepository userRolesRepository;
    private final UserRoleMapper userRoleMapper;
    private final RoleService roleService;
    private final UserService userService;

    public UserRoleServiceImpl(UserRolesRepository userRolesRepository, UserRoleMapper userRoleMapper,
                               RoleService roleService, UserService userService) {
        this.userRolesRepository = userRolesRepository;
        this.userRoleMapper = userRoleMapper;
        this.roleService = roleService;
        this.userService = userService;
    }


    @Override
    public Boolean existsUserRole(int userId, int roleId) {
        Boolean exists = userRolesRepository.existsByUserIdAndRoleId(userId, roleId);
        logger.info("Called Check existsUserRole({}, {}, {})", userId, roleId, exists);
        return exists;
    }

    @Override
    public UserRoleResponseDto findUserRoleById(int id) {
        logger.info("Find user role by id: " + id);
        UserRoles userroles = userRolesRepository.findById(id);
        return userRoleMapper.toResponse(userroles);
    }

    @Override
    public List<UserRoleResponseDto> findUserRolesByUserID(int userID) {
        logger.info("Called find user role by user id {}", userID);
        List<UserRoles> userRoles = userRolesRepository.findByUserId(userID);
        return userRoleMapper.toResponseList(userRoles);
    }

    @Override
    public List<UserRoleResponseDto> findUsersRoleByRoleId(int roleID) {
        logger.info("Called find user role by role id {}", roleID);
        List<UserRoles> userRoles = userRolesRepository.findByRoleId(roleID);
        return userRoleMapper.toResponseList(userRoles);
    }

    @Override
    public UserRoleResponseDto createUserRole(CreateUserRoleRequestDto createUserRoleRequestDto) {
        if (createUserRoleRequestDto == null){
            logger.info("Create user role with null request");
            throw new CustomException(ResponseCodes.NULL_CODE);
        }

        logger.info("Called Create user role with request: {}, {}, {}", createUserRoleRequestDto.getUserId(),
                createUserRoleRequestDto.getRoleId(), createUserRoleRequestDto.getMainRole());

        UserRoles userRoles = userRoleMapper.toEntity(createUserRoleRequestDto);
        userRoles.setCreatedOn(LocalDateTime.now());
        userRoles.setCreatedBy("Nika");

        if (!roleService.existsRole(createUserRoleRequestDto.getRoleId())) {
            logger.info("Role with id {} not exists", createUserRoleRequestDto.getRoleId());
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }

        if (!userService.existsUser(createUserRoleRequestDto.getUserId())) {
            logger.info("User with id {} not exists", createUserRoleRequestDto.getUserId());
            throw new CustomException(ResponseCodes.INVALID_USER);
        }

        if (existsUserRole(createUserRoleRequestDto.getUserId(), createUserRoleRequestDto.getRoleId())) {
            logger.info("User id '{}' already have role with id '{}' ", createUserRoleRequestDto.getUserId(),
                    createUserRoleRequestDto.getRoleId());
            throw new CustomException(ResponseCodes.INVALID_USER);
        }

        userRolesRepository.save(userRoles);
        return userRoleMapper.toResponse(userRoles);
    }


    @Override
    public void deleteUserRole(int id) {
        logger.info("Deleting user role with id: " + id);
        UserRoles userRoles = userRolesRepository.findById(id);

        if (userRoles == null) {
            logger.info("User role with id {} not exists", id);
            throw new CustomException(ResponseCodes.INVALID_USER);
        }

        userRolesRepository.deleteById(id);
    }

    @Override
    public List<UserRoleResponseDto> findAllUserRoles() {
        logger.info("Called Find All User Roles");
        List<UserRoles> userRoles = userRolesRepository.findAll();
        return userRoleMapper.toResponseList(userRoles);
    }
}

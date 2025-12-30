package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.entity.UserRoles;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.UserRoleMapper;
import org.example.incidentmanagement.repository.UserRolesRepository;
import org.example.incidentmanagement.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    private UserRolesRepository userRolesRepository;
    private final UserRoleMapper userRoleMapper;
    private final RoleService roleService;
    private final UserService userService;
    private final CurrentUserService currentUserService;
    private final DefaultConverter defaultConverter;

    public UserRoleServiceImpl(UserRolesRepository userRolesRepository, UserRoleMapper userRoleMapper,
                               RoleService roleService, UserService userService,
                               CurrentUserService currentUserService, DefaultConverter defaultConverter) {
        this.userRolesRepository = userRolesRepository;
        this.userRoleMapper = userRoleMapper;
        this.roleService = roleService;
        this.userService = userService;
        this.currentUserService = currentUserService;
        this.defaultConverter = defaultConverter;
    }


    @Override
    public Boolean existsUserRole(int userId, int roleId) {
        Boolean exists = userRolesRepository.existsByUserIdAndRoleId(userId, roleId);
        logger.info("Called Check existsUserRole({}, {}, {})", userId, roleId, exists);
        return exists;
    }

    @Override
    public UserRoleResponseDto findUserRoleById(int id) {
        logger.info("Find user role by id: {}",  id);
        UserRoles userroles = userRolesRepository.findUserRolesByID(id);

        if (userroles == null) {
            throw new CustomException(ResponseCodes.INVALID_USER_ROLE);
        }

        return userRoleMapper.toResponse(userroles);
    }

    @Override
    public List<UserRoleResponseDto> findUserRolesByUserID(int userID) {
        logger.info("Called find user role by user id {}", userID);
        List<UserRoles> userRoles = userRolesRepository.findByUserId(userID);
        if (userRoles == null) {
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }
        List<UserRoleResponseDto> userRoleResponseDtos = userRoles.stream()
                .map(userRole -> {
                    UserRoleResponseDto userRoleResult = userRoleMapper.toResponse(userRole);

                    String createdBy = defaultConverter.getUserFullName(userRole.getCreatedBy());
                    String updatedBy = defaultConverter.getUserFullName(userRole.getUpdatedBy());
                    String roleName = defaultConverter.getRoleName(userRole.getRoleId()); ;
                    String userName = defaultConverter.getUserFullName(userRole.getUserId());

                    userRoleResult.setUserName(userName);
                    userRoleResult.setRoleName(roleName);
                    userRoleResult.setCreatedBy(createdBy);
                    userRoleResult.setUpdatedBy(updatedBy);

                    return userRoleResult;
                }).toList();
        return userRoleResponseDtos;
    }

    @Override
    public List<UserRoleResponseDto> findUsersRoleByRoleId(int roleID) {
        logger.info("Called find user role by role id {}", roleID);
        List<UserRoles> userRoles = userRolesRepository.findByRoleId(roleID);

        if (userRoles == null) {
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }
        List<UserRoleResponseDto> userRoleResponseDtos = userRoles.stream()
                .map(userRole -> {
                    UserRoleResponseDto userRoleResult = userRoleMapper.toResponse(userRole);

                    String createdBy = defaultConverter.getUserFullName(userRole.getCreatedBy());
                    String updatedBy = defaultConverter.getUserFullName(userRole.getUpdatedBy());
                    String roleName = defaultConverter.getRoleName(userRole.getRoleId()); ;
                    String userName = defaultConverter.getUserFullName(userRole.getUserId());

                    userRoleResult.setUserName(userName);
                    userRoleResult.setRoleName(roleName);
                    userRoleResult.setCreatedBy(createdBy);
                    userRoleResult.setUpdatedBy(updatedBy);

                    return userRoleResult;
                }).toList();
        return userRoleResponseDtos;
    }


    @Override
    public UserRoleResponseDto createUserRole(CreateUserRoleRequestDto createUserRoleRequestDto) {
        logger.info("Called Create user role with request: {}, {}, {}", createUserRoleRequestDto.getUserId(),
                createUserRoleRequestDto.getRoleId(), createUserRoleRequestDto.getMainRole());


        UserRoles userRoles = userRoleMapper.toEntity(createUserRoleRequestDto);

        userRoles.setCreatedOn(LocalDateTime.now());
        userRoles.setStatus("A");
        userRoles.setMainRole(defaultConverter.booleanToString(createUserRoleRequestDto.getMainRole()));
        userRoles.setCreatedBy(currentUserService.getCurrentUserId());


        if (!roleService.existsRole(createUserRoleRequestDto.getRoleId())) {
            logger.info("Role with id {} not exists", createUserRoleRequestDto.getRoleId());
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }

        if (!userService.existsUser(createUserRoleRequestDto.getUserId())) {
            logger.info("User with id {} not exists", createUserRoleRequestDto.getUserId());
            throw new CustomException(ResponseCodes.INVALID_USER);
        }
        UserRoles beforeSaveCheck = userRolesRepository.existUserRolesbyUserID(createUserRoleRequestDto.getUserId());

        if (beforeSaveCheck != null) {
            logger.info("User with id {} already exists", createUserRoleRequestDto.getUserId());
            throw new CustomException(ResponseCodes.USER_ROLE_EXIST);
        }
        userRolesRepository.save(userRoles);

        return userRoleMapper.toResponse(userRoles);
    }


    @Override
    public void deleteUserRole(int id) {
        logger.info("Deleting user role with id: {}", id);
        Optional<UserRoles> userRoles = userRolesRepository.findById(id);

        if (userRoles == null) {
            logger.info("User role with id {} not exists", id);
            throw new CustomException(ResponseCodes.INVALID_USER_ROLE);
        }

        userRolesRepository.deleteById(id);
    }

    @Override
    public List<UserRoleResponseDto> findAllUserRoles() {
        logger.info("Called Find All User Roles");
        List<UserRoles> userRoles = userRolesRepository.findAll();
        List<UserRoleResponseDto> userRoleResponseDtos = userRoles.stream()
                .map(userRole -> {
                    UserRoleResponseDto userRoleResult = userRoleMapper.toResponse(userRole);

                    String createdBy = defaultConverter.getUserFullName(userRole.getCreatedBy());
                    String updatedBy = defaultConverter.getUserFullName(userRole.getUpdatedBy());
                    String roleName = defaultConverter.getRoleName(userRole.getRoleId()); ;
                    String userName = defaultConverter.getUserFullName(userRole.getUserId());

                    userRoleResult.setUserName(userName);
                    userRoleResult.setRoleName(roleName);
                    userRoleResult.setCreatedBy(createdBy);
                    userRoleResult.setUpdatedBy(updatedBy);

                    return userRoleResult;
                }).toList();
        return userRoleResponseDtos;
    }


    @Override
    public Boolean existUserRole(int id, String type){
        logger.info("Called existUserRole({}, {}, {})", id, type);
        Boolean existsUserRole;
        if (type.equals("ROLE")){
            existsUserRole = true;
            return existsUserRole;
        }
        if (type.equals("USER")){
            existsUserRole = false;
            return existsUserRole;
        }

        return true;
    }


    @Override
    public Integer findUserRoleId(Integer userID, Integer roleID){
        logger.info("Called Get User Roles ID");
        if (userID == null || roleID == null ){
            throw new CustomException(ResponseCodes.INVALID_USER_ROLE);
        }

        Integer resultId = userRolesRepository.findUserRoleId(userID, roleID);

        return resultId;
    }
}

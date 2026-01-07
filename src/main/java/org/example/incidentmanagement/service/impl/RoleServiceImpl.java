package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.RoleRequestDto;
import org.example.incidentmanagement.dto.response.RoleResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateRoleRequestDto;
import org.example.incidentmanagement.entity.Role;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.RoleMapper;
import org.example.incidentmanagement.repository.RoleRepository;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.interfaces.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final CurrentUserService currentUserService;


    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, CurrentUserService currentUserService
                           ) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.currentUserService = currentUserService;
    }


    @Override
    public Boolean existsRole(int id) {
        Boolean exists = roleRepository.existsById(id);
        logger.info("Called Check Role Exists on ID '{}' , Result: '{}'", id, exists  );
        return exists;
    }

    @Override
    public RoleResponseDto findByRoleName(String roleName) {
        logger.info("Called Find Role Named: {}", roleName);
        if (roleName == null) {
            logger.info("Role Name is provided Null");
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }
        if (roleName.isEmpty()) {
            logger.info("findByRoleName() called with empty roleName");
        }
        if (roleRepository.findByName(roleName) == null) {
            logger.info("Role with name {} not found", roleName);
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }

        RoleResponseDto roleResult = roleMapper.toRoleResponseDto(roleRepository.findByName(roleName));

        return roleResult;

    }


    @Override
    public RoleResponseDto createRole(RoleRequestDto roledto) {
        logger.info("called Create Role");
        if (roledto == null) {
            logger.info("Role is null");
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }
        if (roleRepository.findByName(roledto.getRoleName()) != null) {
            logger.info("Role with name {} already exists", roledto.getRoleName());
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }

        Role role = roleMapper.toEntityDetails(roledto, currentUserService.getCurrentUserId());
        roleRepository.save(role);


        RoleResponseDto roleResult = roleMapper.toRoleResponseDto(role);
        return roleResult;

    }

    @Override
    public RoleResponseDto updateRole(String name, UpdateRoleRequestDto role) {
        logger.info("called Update Role");

        Role roleToUpdate = roleRepository.findByName(name);

        if (roleToUpdate == null) {
            logger.info("Update Role is null");
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }

        roleMapper.updateFromDto(role, roleToUpdate);
        roleToUpdate.setUpdatedOn(LocalDateTime.now());
        //roleToUpdate.setUpdatedBy("Nika"); //აქ მერე უნდა მიეთითოს იუზერი ავტომატში

        Role updatedRole = roleRepository.save(roleToUpdate);
        return roleMapper.toRoleResponseDto(updatedRole);

    }

    @Override
    public void deleteRole(String name) {
        logger.info("called Delete Role : {}", name );

        if (name == null) {
            logger.info("delete() called with null role");
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }
        if (roleRepository.findByName(name) == null) {
            logger.info("Role with name {} not found", name);
            throw new CustomException(ResponseCodes.INVALID_ROLE);
        }
        Role role = roleRepository.findByName(name);

        roleRepository.delete(role);


    }

    @Override
    public List<Role> findAll() {
        logger.info("called Find All Roles");
        List<Role> roles = roleRepository.findAll();
        return roles;
    }
}

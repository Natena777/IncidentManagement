package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.RoleRequestDto;
import org.example.incidentmanagement.dto.response.RoleResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateRoleRequestDto;
import org.example.incidentmanagement.entity.Role;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ErrorCodes;
import org.example.incidentmanagement.mappers.RoleMapper;
import org.example.incidentmanagement.repository.RoleRepository;
import org.example.incidentmanagement.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleResponseDto findByRoleName(String roleName) {
        logger.info("Called Find Role Named: " + roleName);
        if (roleName == null) {
            logger.info("Role Name is provided Null");
            throw new CustomException(ErrorCodes.INVALID_ROLE);
        }
        if (roleName.isEmpty()) {
            logger.info("findByRoleName() called with empty roleName");
        }
        if (roleRepository.findByName(roleName) == null) {
            logger.info("Role with name {} not found", roleName);
            throw new CustomException(ErrorCodes.INVALID_ROLE);
        }

        return roleMapper.toRoleResponseDto(roleRepository.findByName(roleName));

    }


    @Override
    public RoleResponseDto createRole(RoleRequestDto roledto) {
        logger.info("called Create Role");
        if (roledto == null) {
            logger.info("Role is null");
            throw new CustomException(ErrorCodes.INVALID_ROLE);
        }
        if (roleRepository.findByName(roledto.getRoleName()) != null) {
            logger.info("Role with name {} already exists", roledto.getRoleName());
            throw new CustomException(ErrorCodes.INVALID_ROLE);
        }

        Role role = roleMapper.toEntity(roledto);
        role.setCreatedOn(LocalDateTime.now());
        role.setStatus("A");
        role.setCreatedBy("Nika"); //აქ უნდა მიეთითოს იუზერი ავტომატში
        roleRepository.save(role);
        return roleMapper.toRoleResponseDto(role);
    }

    @Override
    public RoleResponseDto updateRole(String name, UpdateRoleRequestDto role) {
        logger.info("called Update Role");

        Role roleToUpdate = roleRepository.findByName(name);

        if (roleToUpdate == null) {
            logger.info("Update Role is null");
            throw new CustomException(ErrorCodes.INVALID_ROLE);
        }

        roleMapper.updateFromDto(role, roleToUpdate);
        roleToUpdate.setUpdatedOn(LocalDateTime.now());
        roleToUpdate.setUpdatedBy("Nika"); //აქ მერე უნდა მიეთითოს იუზერი ავტომატში

        Role updatedRole = roleRepository.save(roleToUpdate);

        return roleMapper.toRoleResponseDto(updatedRole);

    }

    @Override
    public void deleteRole(String name) {
        logger.info("called Delete Role : " + name );

        if (name == null) {
            logger.info("delete() called with null role");
            throw new CustomException(ErrorCodes.INVALID_ROLE);
        }
        if (roleRepository.findByName(name) == null) {
            logger.info("Role with name {} not found", name);
            throw new CustomException(ErrorCodes.INVALID_ROLE);
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

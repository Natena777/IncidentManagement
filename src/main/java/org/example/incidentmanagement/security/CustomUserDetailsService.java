package org.example.incidentmanagement.security;

import org.example.incidentmanagement.entity.Role;
import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.entity.UserRoles;
import org.example.incidentmanagement.repository.RoleRepository;
import org.example.incidentmanagement.repository.UserRepository;
import org.example.incidentmanagement.repository.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private RoleRepository roleRepository;
    private UserRolesRepository userRolesRepository;

    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository,
                                    UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRolesRepository = userRolesRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Security Load user by username: {}", username);
        User user = userRepository.findByUsername(username);
        UserRoles userRoles = userRolesRepository.findByUserId(user.getId());


        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Role role = roleRepository.findById(userRoles.getRoleId())
                .orElseThrow(() -> new UsernameNotFoundException("Role not found "));


        return new CustomUserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getUsername(),
                role.getName()
        );
    }
}

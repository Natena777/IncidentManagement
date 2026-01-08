package org.example.incidentmanagement.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.incidentmanagement.entity.User;
import org.example.incidentmanagement.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public Integer getCurrentUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        if (user != null) {
            return user.getId();
        }

        return null;

    }

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }
}

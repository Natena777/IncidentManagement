package org.example.incidentmanagement.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class EndpointPermissions {

    public static void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {

        //დაცული Enpoint ები
        // კონკრეტულ როლებზე დამატებული წვდომები.
        auth
                .requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyAuthority("CREATOR", "ADMIN");


        // ADMIN only
        auth
                .requestMatchers("/api/roles/**").hasAuthority("ADMIN")
                .requestMatchers("/api/user-roles/**").hasAuthority("ADMIN")
                .requestMatchers("/api/assignee-groups/**").hasAuthority("ADMIN")
                .requestMatchers("/api/case-statuses/**").hasAuthority("ADMIN")
                .requestMatchers("/api/service-catalog/**").hasAuthority("ADMIN")
                .requestMatchers("/api/users/**").hasAuthority("ADMIN");

    }

}

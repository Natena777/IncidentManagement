package org.example.incidentmanagement.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class EndpointPermissions {

    public static void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {

        //დაცული Enpoint ები
        // კონკრეტულ როლებზე დამატებული წვდომები.
        auth
                .requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyAuthority("CREATOR", "SUPER_ADMIN");


        // ADMIN only
        auth
                .requestMatchers("/api/role/**").hasAuthority("SUPER_ADMIN")
                .requestMatchers("/api/userRole/**").hasAuthority("SUPER_ADMIN")
                .requestMatchers("/api/assigneeGroup/**").hasAuthority("SUPER_ADMIN")
                .requestMatchers("/api/status/**").hasAuthority("SUPER_ADMIN")
                .requestMatchers("/api/servicesCatalog/**").hasAuthority("SUPER_ADMIN")
                .requestMatchers("/api/users/**").hasAuthority("SUPER_ADMIN");

    }


}

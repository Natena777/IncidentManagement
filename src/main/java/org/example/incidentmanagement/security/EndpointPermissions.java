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
                .requestMatchers("/api/role/**").hasAuthority("ADMIN")
                .requestMatchers("/api/userRole/**").hasAuthority("ADMIN")
                .requestMatchers("/api/assigneeGroup/**").hasAuthority("ADMIN")
                .requestMatchers("/api/status/**").hasAuthority("ADMIN")
                .requestMatchers("/api/servicesCatalog/**").hasAuthority("ADMIN")
                .requestMatchers("/api/users/**").hasAuthority("ADMIN");

    }


}

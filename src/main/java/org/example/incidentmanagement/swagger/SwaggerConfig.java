package org.example.incidentmanagement.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI baseOpenAPI() {

        // 🔐 JWT Bearer scheme
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .info(new Info()
                        .title("Incident Management API")
                        .version("1.0.0")
                        .description("User Registration, Login and Management API")
                )
                // 👇 აქ ვამატებთ security scheme-ს
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", bearerScheme)
                )
                // 👇 და ვეუბნებით Swagger-ს, რომ გამოიყენოს
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth")
                );
    }
}

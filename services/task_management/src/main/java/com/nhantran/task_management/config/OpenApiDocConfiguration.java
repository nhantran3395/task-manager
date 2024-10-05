package com.nhantran.task_management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(type = SecuritySchemeType.OAUTH2, name = "bearer_token", in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(
        info = @Info(title = "Task Management Service API"),
        security = { @SecurityRequirement(name = "bearer_token") }
)
public class OpenApiDocConfiguration {
}

package br.com.bonnafood.app.common.api.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfigurationOpenApi {
    private static final String SECURITY_SCHEMA_NAME = "Bonnafood";
    private static final String MODULE_NAME = "Bonnafood";
    private static final String API_VERSION = "1.0";

    public SecurityConfigurationOpenApi() {
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String apiTitle = "%s API".formatted(StringUtils.capitalize(MODULE_NAME));
        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement().addList(SECURITY_SCHEMA_NAME))
                .components(
                        new Components()
                                .addSecuritySchemes(SECURITY_SCHEMA_NAME, securityScheme()))
                .info(new Info().title(apiTitle).version(API_VERSION));
    }

    SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name(SECURITY_SCHEMA_NAME)
                .type(SecurityScheme.Type.OAUTH2)
                .scheme("oauth2")
                .bearerFormat("jwt")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .flows(new OAuthFlows().password(new OAuthFlow().tokenUrl("/oauth/token")));
    }

}

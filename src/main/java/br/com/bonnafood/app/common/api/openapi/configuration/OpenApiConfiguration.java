package br.com.bonnafood.app.common.api.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    private static final String SECURITY_SCHEMA_NAME = "bonnasys-security";
    private final OpenApiProperties properties;

    @Autowired
    public OpenApiConfiguration(OpenApiProperties properties) {
        this.properties = properties;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(getSecurityItem())
                .components(getComponents())
                .info(getInfo());
    }

    private Components getComponents() {
        return new Components().addSecuritySchemes(SECURITY_SCHEMA_NAME, securityScheme());
    }

    private SecurityRequirement getSecurityItem() {
        return new SecurityRequirement().addList(SECURITY_SCHEMA_NAME);
    }


    private Info getInfo() {
        final String apiTitle = "%s API".formatted(StringUtils.capitalize(properties.getModule()));
        return new Info()
                .description(properties.getDescription())
                .version(properties.getApiVersion())
                .contact(getContact())
                .title(apiTitle);
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail(properties.getContactEmail());
        contact.setName(properties.getContactName());
        contact.setUrl(properties.getContactUrl());
        return contact;
    }

    private SecurityScheme securityScheme() {
        OAuthFlow oAuthFlow = new OAuthFlow()
                .tokenUrl("http://localhost:8081/oauth/token")
                .authorizationUrl("http://localhost:8081/oauth/token")
                .scopes(new Scopes().addString("READ", "WRITE"));

        return new SecurityScheme()
                .name(SECURITY_SCHEMA_NAME)
                .flows(new OAuthFlows().password(oAuthFlow))
                .type(SecurityScheme.Type.OAUTH2).scheme("oauth2");
    }

    @Deprecated //Deprecado pelas configurações de servidor externo
    private SecurityScheme securitySchemeWithPasswordCredencials() {
        return new SecurityScheme()
                .name(SECURITY_SCHEMA_NAME)
                .type(SecurityScheme.Type.OAUTH2)
                .scheme("oauth2")
                .bearerFormat("jwt")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .flows(new OAuthFlows()
                        .password(new OAuthFlow()
                                .tokenUrl("/oauth/token")));
    }

}

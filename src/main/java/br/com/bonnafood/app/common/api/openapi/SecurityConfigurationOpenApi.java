package br.com.bonnafood.app.common.api.openapi;

import br.com.bonnafood.app.common.api.openapi.configuration.OpenApiProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SecurityConfigurationOpenApi {
    private static final String SECURITY_SCHEMA_NAME = "bonnasys-security";
    private final OpenApiProperties properties;

    @Autowired
    public SecurityConfigurationOpenApi(OpenApiProperties properties) {
        this.properties = properties;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(getSecurityItem())
                .components(getComponents())
                .servers(getServerList())
                .info(getInfo());
    }

    private Components getComponents() {
        return new Components().addSecuritySchemes(SECURITY_SCHEMA_NAME, securityScheme());
    }

    private SecurityRequirement getSecurityItem() {
        return new SecurityRequirement().addList(SECURITY_SCHEMA_NAME);
    }

    private List<Server> getServerList() {
        Server localServer = getLocalServerConfig();
        Server remoteServer = getRemoteServerConfig();
        return List.of(localServer, remoteServer);
    }

    private Server getRemoteServerConfig() {
        Server server = new Server();
        server.setUrl("http://localhost:8081");
        server.setDescription("REMOTE_SERVER");
        return server;
    }

    private Server getLocalServerConfig() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("LOCAL_SERVER");
        return server;
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

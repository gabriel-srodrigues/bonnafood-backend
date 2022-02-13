package br.com.bonnafood.app.common.api.openapi.configuration;

import br.com.bonnafood.app.common.api.openapi.LinkModelOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;

@Configuration
public class OpenApiAlternativeClasses {
    @Bean
    public void configureAlternativeClasses() {
        SpringDocUtils.getConfig().replaceWithClass(Link.class, LinkModelOpenApi.class);

    }
}

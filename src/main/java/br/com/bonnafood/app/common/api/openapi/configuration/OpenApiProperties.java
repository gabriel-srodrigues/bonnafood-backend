package br.com.bonnafood.app.common.api.openapi.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("opendocument")
public class OpenApiProperties {
    @NotBlank
    private String description;
    @NotBlank
    private String apiVersion;
    @NotBlank
    private String contactName;
    @NotBlank
    private String contactEmail;
    @NotBlank
    private String contactUrl;
    @NotBlank
    private String module;

}

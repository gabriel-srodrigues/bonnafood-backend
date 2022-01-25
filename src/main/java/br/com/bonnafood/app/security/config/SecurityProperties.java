package br.com.bonnafood.app.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("bonnafood.security")
public class SecurityProperties {
    private boolean disabled = false;
    private String defaultUserIdIfDisabled;
    private String logoutDefaultRedirectUrl = "/";
    private String signingKey;
    private String algorithm;

}

package br.com.bonnafood.app.security;

import br.com.bonnafood.app.security.config.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {
    private final SecurityProperties securityProperties;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/swagger-ui",
                        "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .cors()
                .and()
                    .oauth2ResourceServer()
                .jwt()
                    .jwtAuthenticationConverter(jwtAuthenticationConverter());
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null) {
                authorities = Collections.emptyList();
            }

            return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        });

        return jwtAuthenticationConverter;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        var secretKey = new SecretKeySpec(securityProperties.getSigningKey().getBytes(), securityProperties.getAlgorithm());
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
package br.com.bonnafood.app.security.authorizationserver;

import br.com.bonnafood.app.security.config.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.List;


@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final SecurityProperties securityProperties;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                    .withClient("bonnafood-recipes")
                    .secret(passwordEncoder.encode("web123"))
                    .authorizedGrantTypes("password", "refresh_token", "client_credentials")
                    .scopes("WRITE", "READ")
                    .accessTokenValiditySeconds(6 * 60 * 60)// 6 horas
                    .refreshTokenValiditySeconds(60 * 24 * 60 * 60) // 60 dias
                .and()
                    .withClient("swagger")
                    .secret(passwordEncoder.encode("swagger"))
                    .authorizedGrantTypes("password", "refresh_token", "client_credentials")
                    .scopes("WRITE", "READ")
                    .accessTokenValiditySeconds(6 * 60 * 60)// 6 horas
                    .refreshTokenValiditySeconds(60 * 24 * 60 * 60) // 60 dias
                .and()
                    .withClient("checktoken")
                    .secret(passwordEncoder.encode("check123"));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        var enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(List.of(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));
        endpoints
                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenEnhancer(enhancerChain)
                .reuseRefreshTokens(false);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(securityProperties.getSigningKey());
        return jwtAccessTokenConverter;
    }

}
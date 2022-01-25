package br.com.bonnafood.app.security.authorizationserver;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (oAuth2Authentication.getPrincipal() instanceof AuthUser authUser) {
            var info = new HashMap<String, Object>();
            info.put("nome_completo", authUser.getFullName());
            info.put("id", authUser.getUserId());

            var accessToken = (DefaultOAuth2AccessToken) oAuth2AccessToken;
            accessToken.setAdditionalInformation(info);
        }
        return oAuth2AccessToken;
    }
}

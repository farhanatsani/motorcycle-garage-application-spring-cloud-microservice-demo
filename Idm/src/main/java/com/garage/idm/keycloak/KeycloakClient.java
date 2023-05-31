package com.garage.idm.keycloak;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

@Component
public class KeycloakClient {
    @Value("${keycloak.credentials.secret}")
    private String secretKey;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.auth-server-url}")
    private String authUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Bean
    public Keycloak keycloakAdminClient(){
        return KeycloakBuilder.builder()
                .grantType(CLIENT_CREDENTIALS)
                .serverUrl(authUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(secretKey)
                .build();
    }
    public AuthzClient authzClient(){
        var clientCredentials = new HashMap<String, Object>();
        clientCredentials.put("secret", secretKey);
        var configuration =
                new Configuration(authUrl, realm, clientId, clientCredentials, null);
        return AuthzClient.create(configuration);
    }
    @Bean
    public RealmResource realmResource(Keycloak keycloak) {
        return keycloak.realm(realm);
    }
    public String getTokenUrl(){
        return authUrl+"/realms/"+realm+"/protocol/openid-connect/token";
    }

    public MultiValueMap<String, String> getClient(){
        MultiValueMap map =  new LinkedMultiValueMap<>();
        map.set("client_id", clientId);
        map.set("client_secret", secretKey);
        return map;
    }
}

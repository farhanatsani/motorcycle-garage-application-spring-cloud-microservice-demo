package com.garage.idm.keycloak;

import lombok.RequiredArgsConstructor;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final KeycloakClient keycloakClient;
    private final RestTemplate restTemplate;

    public AccessTokenResponse authenticate(String username, char[] password) {
        try {

            AuthzClient authzClient = keycloakClient.authzClient();
            AccessTokenResponse authResponse = authzClient.obtainAccessToken(username, new String(password));

            return authResponse;

        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
    public AccessTokenResponse refreshToken(String refreshToken) {
        try {
            Assert.notNull(refreshToken, "Refresh token is null");

            MultiValueMap<String, String> refreshTokenRequest = keycloakClient.getClient();
            refreshTokenRequest.set("refresh_token", refreshToken);
            refreshTokenRequest.set("grant_type", "refresh_token");

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            headers.set(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE);
            HttpEntity request = new HttpEntity<>(refreshTokenRequest,headers);

            ResponseEntity<AccessTokenResponse> authResponse =  restTemplate.postForEntity(keycloakClient.getTokenUrl(), request, AccessTokenResponse.class);

            /*
                        AccessTokenResponse accessTokenResponse = webClient.post().uri(keycloakClient.getTokenUrl())
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(refreshTokenRequest)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError,
                            clientResponse -> clientResponse.bodyToMono(String.class).map(RuntimeException::new))
                    .onStatus(HttpStatus::is5xxServerError,
                            clientResponse -> clientResponse.bodyToMono(String.class).map(RuntimeException::new))
                    .bodyToMono(AccessTokenResponse.class)
                    .block();
             */

            return authResponse.getBody();

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}

package com.garage.idm.login;

import com.garage.idm.user.UserDTO;
import com.garage.idm.user.UserRepository;
import com.garage.idm.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
//@AllArgsConstructor
public class LoginService {
    private UserService userService;
    private AuthzClient authzClient;
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {

        UserDTO user = userService.getUser(loginRequest.getUsername());

        AccessTokenResponse tokenResponse = authzClient
                .obtainAccessToken(loginRequest.getUsername(), loginRequest.getPassword());

        if(tokenResponse.getError() != null) {
            log.info("Error[{}], Error desc: {}", tokenResponse.getError(), tokenResponse.getErrorDescription());
        }

        return LoginResponseDTO.builder()
                .username(user.getUsername())
                .token(tokenResponse.getToken())
                .build();
    }
}

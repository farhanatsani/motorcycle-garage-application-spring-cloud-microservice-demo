package com.garage.idm.keycloak;

import com.garage.idm.login.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO authenticationDto) {
        return ResponseEntity.ok(this.authenticationService.authenticate(authenticationDto.getUsername(),authenticationDto.getPassword().toCharArray()));
    }

    @PostMapping(value = "/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refreshToken(@RequestParam("refresh_token") String refreshToken) {
        return ResponseEntity.ok(this.authenticationService.refreshToken(refreshToken));
    }
}

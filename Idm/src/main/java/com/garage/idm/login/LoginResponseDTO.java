package com.garage.idm.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String username;
    private String token;
}

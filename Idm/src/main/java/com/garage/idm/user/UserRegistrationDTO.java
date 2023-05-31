package com.garage.idm.user;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDTO {
    @NotEmpty(message = "username must not empty")
    private String username;
    @NotEmpty(message = "name must not empty")
    private String name;
    @NotEmpty(message = "phoneNumber must not empty")
    private String phoneNumber;
    @NotEmpty(message = "email must not empty")
    private String email;
}

package com.garage.idm.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String username;
    private String name;
    private String phoneNumber;
    private String email;
    private String roles;
}

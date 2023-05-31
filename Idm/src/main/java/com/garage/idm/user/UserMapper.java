package com.garage.idm.user;

public class UserMapper {
    public static UserEntity toUserEntity(UserRegistrationDTO userRegistrationDTO) {
        return UserEntity.builder()
                .username(userRegistrationDTO.getUsername())
                .name(userRegistrationDTO.getName())
                .phoneNumber(userRegistrationDTO.getPhoneNumber())
                .email(userRegistrationDTO.getEmail())
                .roles("USER")
                .build();
    }
    public static UserDTO toUserDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .phoneNumber(userEntity.getPhoneNumber())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .build();
    }
}

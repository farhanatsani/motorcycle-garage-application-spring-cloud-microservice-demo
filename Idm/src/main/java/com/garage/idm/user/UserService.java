package com.garage.idm.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = UserMapper.toUserEntity(userRegistrationDTO);
        userRepository.saveAndFlush(user);
        return UserMapper.toUserDTO(user);
    }
    public UserDTO getUser(String username) {
        UserEntity user = userRepository.findByUsernameIgnoreCase(username)
                .stream()
                .findAny()
                .orElseThrow(NullPointerException::new);

        return UserMapper.toUserDTO(user);
    }
}

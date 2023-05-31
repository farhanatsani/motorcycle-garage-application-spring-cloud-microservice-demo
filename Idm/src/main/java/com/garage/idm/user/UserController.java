package com.garage.idm.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @RolesAllowed("user")
    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        UserDTO userRegistered = userService.registerUser(userRegistrationDTO);
        return ResponseEntity.ok(userRegistered);
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        UserDTO user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }
}

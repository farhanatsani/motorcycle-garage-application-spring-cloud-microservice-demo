package com.garage.mechanics;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/mechanics")
@AllArgsConstructor
public class MechanicController {
    private final MechanicService mechanicService;
    @PostMapping
    public ResponseEntity<RegistartionMechanicResponse> registerMechanic(@RequestBody RegistrationMechanicRequest registrationMechanicRequest) {

        log.info("New mechanic register {}", registrationMechanicRequest);

        Mechanic mechanicRegistered = mechanicService.registerMechanic(registrationMechanicRequest);

        RegistartionMechanicResponse response = RegistartionMechanicResponse.builder()
                .id(mechanicRegistered.getId().toString())
                .name(registrationMechanicRequest.getName())
                .phoneNumber(registrationMechanicRequest.getPhoneNumber())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<MechanicResponse>> findMechanics() {
        List<MechanicResponse> mechanics = mechanicService.findMechanics();
        return ResponseEntity.status(HttpStatus.OK).body(mechanics);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<MechanicResponse>> findMechanics(@PathVariable UUID id) {
        List<MechanicResponse> mechanics = mechanicService.findMechanic(id);
        return ResponseEntity.status(HttpStatus.OK).body(mechanics);
    }
}

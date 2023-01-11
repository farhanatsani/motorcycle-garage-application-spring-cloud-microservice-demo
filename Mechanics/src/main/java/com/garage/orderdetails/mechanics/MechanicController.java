package com.garage.orderdetails.mechanics;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mechanics")
@AllArgsConstructor
public class MechanicController {
    private final MechanicService mechanicService;
    @PostMapping
    public ResponseEntity<RegistartionMechanicResponse> registerMechanic(@RequestBody RegistrationMechanicRequest registrationMechanicRequest) {

        Mechanic mechanicRegistered = mechanicService.registerMechanic(registrationMechanicRequest);

        RegistartionMechanicResponse response = RegistartionMechanicResponse.builder()
                .id(mechanicRegistered.getId().toString())
                .name(registrationMechanicRequest.getName())
                .phoneNumber(registrationMechanicRequest.getPhoneNumber())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

package com.garage.orderdetails.mechanics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MechanicService {
    private final MechanicRepository mechanicRepository;
    public Mechanic registerMechanic(RegistrationMechanicRequest registrationMechanicRequest) {
        Mechanic mechanic = Mechanic.builder()
                .name(registrationMechanicRequest.getName())
                .phoneNumber(registrationMechanicRequest.getPhoneNumber())
                .build();
        return mechanicRepository.save(mechanic);
    }
}

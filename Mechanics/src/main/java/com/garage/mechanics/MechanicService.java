package com.garage.mechanics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<MechanicResponse> findMechanics() {
        List<Mechanic> mechanics = mechanicRepository.findAll();
        List<MechanicResponse> mechanicResponses = mechanics
                .stream()
                .map(mechanic ->
                        MechanicResponse.builder()
                                .id(mechanic.getId().toString())
                                .name(mechanic.getName())
                                .phoneNumber(mechanic.getPhoneNumber())
                                .build())
                .collect(Collectors.toList());
        return mechanicResponses;
    }
    public List<MechanicResponse> findMechanic(UUID id) {
        List<Mechanic> mechanics = mechanicRepository.findById(id).stream().collect(Collectors.toList());
        List<MechanicResponse> mechanicResponses = mechanics
                .stream()
                .map(mechanic ->
                        MechanicResponse.builder()
                                .id(mechanic.getId().toString())
                                .name(mechanic.getName())
                                .phoneNumber(mechanic.getPhoneNumber())
                                .build())
                .collect(Collectors.toList());
        return mechanicResponses;
    }
}

package com.garage.vehicles;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    public Vehicle registerVehicle(VehicleRegistrationRequest request) {
        Optional<Vehicle> vehicleRegistered = vehicleRepository.findByPlateNumber(request.getPlateNumber());
        if(vehicleRegistered.isPresent()) {
            throw new RuntimeException("Vehicle already registered");
        }

        Vehicle vehicle = Vehicle.builder()
                .brand(request.getBrand())
                .color(request.getColor())
                .plateNumber(request.getPlateNumber())
                .year(request.getYear())
                .build();
        return vehicleRepository.save(vehicle);
    }

    public List<VehicleResponse> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleResponse> vehicleResponses = vehicles
                .stream()
                .map(vehicle ->
                        VehicleResponse.builder()
                                .id(vehicle.getId().toString())
                                .plateNumber(vehicle.getPlateNumber())
                                .brand(vehicle.getBrand())
                                .color(vehicle.getColor())
                                .year(vehicle.getYear())
                                .build())
                .collect(Collectors.toList());
        return vehicleResponses;
    }

    public List<VehicleResponse> findById(UUID id) {
        List<Vehicle> vehicles = vehicleRepository.findById(id).stream().collect(Collectors.toList());
        List<VehicleResponse> vehicleResponses = vehicles
                .stream()
                .map(vehicle ->
                        VehicleResponse.builder()
                                .id(vehicle.getId().toString())
                                .plateNumber(vehicle.getPlateNumber())
                                .brand(vehicle.getBrand())
                                .color(vehicle.getColor())
                                .year(vehicle.getYear())
                                .build())
                .collect(Collectors.toList());
        return vehicleResponses;
    }
}

package com.garage.orderdetails.vehicles;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    public Vehicle registerVehicle(VehicleRegistrationRequest request) {
        Vehicle vehicle = Vehicle.builder()
                .brand(request.getBrand())
                .color(request.getColor())
                .plateNumber(request.getPlateNumber())
                .year(request.getYear())
                .build();
        return vehicleRepository.save(vehicle);
    }
}

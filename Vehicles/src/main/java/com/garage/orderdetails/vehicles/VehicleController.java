package com.garage.orderdetails.vehicles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    @PostMapping
    public ResponseEntity<VehicleRegistrationResponse> registerVehicle(@RequestBody VehicleRegistrationRequest vehicleRegistrationRequest) {
        log.info("New Vehicle Registration {}", vehicleRegistrationRequest);

        Vehicle vehicleRegistered = vehicleService.registerVehicle(vehicleRegistrationRequest);

        VehicleRegistrationResponse response = VehicleRegistrationResponse.builder()
                .plateNumber(vehicleRegistrationRequest.getPlateNumber())
                .brand(vehicleRegistrationRequest.getBrand())
                .color(vehicleRegistrationRequest.getColor())
                .year(vehicleRegistrationRequest.getYear())
                .id(vehicleRegistered.getId().toString())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}

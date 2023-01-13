package com.garage.vehicles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @GetMapping
    public ResponseEntity<List<VehicleResponse>> findAll() {

        log.info("Get all vehicles");

        List<VehicleResponse> vehicles = vehicleService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(vehicles);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<VehicleResponse>> findById(@PathVariable UUID id) {

        log.info("Get vehicleById {}", id);

        List<VehicleResponse> vehiles = vehicleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(vehiles);
    }
}

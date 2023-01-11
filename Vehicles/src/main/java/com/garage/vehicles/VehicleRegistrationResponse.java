package com.garage.vehicles;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleRegistrationResponse {
    private String id;
    private String plateNumber;
    private String brand;
    private String color;
    private String year;
}

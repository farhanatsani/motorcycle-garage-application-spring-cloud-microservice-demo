package com.garage.orderdetails.vehicles;

import lombok.Data;

@Data
public class VehicleRegistrationRequest {
    private String plateNumber;
    private String brand;
    private String color;
    private String year;
}

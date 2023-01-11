package com.garage.orderdetails.mechanics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationMechanicRequest {
    private String name;
    private String phoneNumber;
}

package com.garage.orderdetails.mechanics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistartionMechanicResponse {
    private String id;
    private String name;
    private String phoneNumber;
}

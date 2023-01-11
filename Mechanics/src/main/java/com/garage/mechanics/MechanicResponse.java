package com.garage.mechanics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MechanicResponse {
    private String id;
    private String name;
    private String phoneNumber;
}

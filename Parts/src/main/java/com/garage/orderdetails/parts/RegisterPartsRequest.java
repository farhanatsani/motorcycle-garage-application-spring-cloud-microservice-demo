package com.garage.orderdetails.parts;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterPartsRequest {
    private String name;
    private BigDecimal price;
}

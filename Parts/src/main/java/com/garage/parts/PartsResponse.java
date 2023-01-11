package com.garage.parts;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PartsResponse {
    private String id;
    private String name;
    private BigDecimal price;
}

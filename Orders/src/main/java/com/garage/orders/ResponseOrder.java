package com.garage.orders;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ResponseOrder {
    private String id;
    private String invoiceNumber;
    private LocalDate date;
    private String plateNumber;
    private String mechanicId;
}

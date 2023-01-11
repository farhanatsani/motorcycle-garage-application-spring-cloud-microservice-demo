package com.garage.orderdetails.orders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestOrder {
    private String invoiceNumber;
    private String plateNumber;
    private String mechanicId;
}

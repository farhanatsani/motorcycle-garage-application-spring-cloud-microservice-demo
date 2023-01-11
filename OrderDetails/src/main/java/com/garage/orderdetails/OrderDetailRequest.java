package com.garage.orderdetails;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private String partsId;
    private String orderId;
}

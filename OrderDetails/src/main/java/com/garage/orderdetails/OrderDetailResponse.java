package com.garage.orderdetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailResponse {
    private String id;
    private String partsId;
    private String orderId;
}

package com.garage.orders;

import com.garage.client.orderdetails.OrderDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String plateNumber;
    private String mechanicId;
    private List<OrderDetailRequest> orderDetail;
}

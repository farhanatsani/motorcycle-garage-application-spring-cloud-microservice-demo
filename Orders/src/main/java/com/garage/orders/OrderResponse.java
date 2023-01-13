package com.garage.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.garage.client.orderdetails.OrderDetailResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private String id;
    private String invoiceNumber;
    private LocalDate date;
    private String plateNumber;
    private String mechanicId;
    private List<OrderDetailResponse> orderDetail;
    private BigDecimal totalPrices;
}

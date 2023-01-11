package com.garage.orderdetails;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    public OrderDetail acceptOrder(OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = OrderDetail.builder()
                .orderId(orderDetailRequest.getOrderId())
                .partsId(orderDetailRequest.getPartsId())
                .build();

        return orderDetailRepository.save(orderDetail);
    }
}

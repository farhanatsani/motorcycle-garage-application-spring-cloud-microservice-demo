package com.garage.orderdetails;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-detail")
@AllArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    @PostMapping
    public ResponseEntity<OrderDetailResponse> acceptOrder(@RequestBody OrderDetailRequest orderDetailRequest) {

        OrderDetail orderDetail = orderDetailService.acceptOrder(orderDetailRequest);

        OrderDetailResponse response = OrderDetailResponse.builder()
                .id(orderDetail.getId().toString())
                .orderId(orderDetail.getOrderId())
                .partsId(orderDetail.getPartsId())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

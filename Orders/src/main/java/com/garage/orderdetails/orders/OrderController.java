package com.garage.orderdetails.orders;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    public ResponseEntity<ResponseOrder> createOrder(@RequestBody RequestOrder requestOrder) {
        Order order = orderService.createOrder(requestOrder);
        ResponseOrder response = ResponseOrder.builder()
                .id(order.getId().toString())
                .invoiceNumber(order.getInvoiceNumber())
                .mechanicId(order.getMechanicId())
                .plateNumber(order.getPlateNumber())
                .date(order.getDate())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}

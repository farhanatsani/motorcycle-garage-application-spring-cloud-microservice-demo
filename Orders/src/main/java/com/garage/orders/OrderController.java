package com.garage.orders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {

        log.info("Create new order {}", orderRequest);

        Order order = orderService.createOrder(orderRequest);
        OrderResponse response = OrderResponse.builder()
                .id(order.getId().toString())
                .invoiceNumber(order.getInvoiceNumber())
                .mechanicId(order.getMechanicId())
                .plateNumber(order.getPlateNumber())
                .date(order.getDate())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrder() {

        log.info("Get Orders");

        List<OrderResponse> orders = orderService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderResponse>> findOrderById(@PathVariable("id") UUID id) {

        log.info("Get orderId {}", id);

        List<OrderResponse> orders = orderService.findByOrderId(id);

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}

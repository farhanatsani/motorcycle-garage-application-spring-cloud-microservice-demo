package com.garage.orderdetails;

import com.garage.client.orderdetails.OrderDetailRequest;
import com.garage.client.orderdetails.OrderDetailResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/order-detail")
@AllArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    @PostMapping
    public ResponseEntity<OrderDetailResponse> saveOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {

        log.info("New order detail {}", orderDetailRequest);

        OrderDetail orderDetail = orderDetailService.saveOrder(orderDetailRequest);

        OrderDetailResponse response = OrderDetailResponse.builder()
                .id(orderDetail.getId().toString())
                .orderId(orderDetail.getOrderId())
                .partsId(orderDetail.getPartsId())
                .quantity(orderDetail.getQuantity())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailResponse>> getAllOrderDetail(@RequestParam(required = false, name = "orderId") String orderId) {

        if(StringUtils.hasText(orderId)) {
            log.info("Get order-detail by orderId: {}", orderId);

            return ResponseEntity.status(HttpStatus.OK).body(orderDetailService.findByOrderId(orderId));
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetailById(@PathVariable UUID id) {
        log.info("Get order-detail by id {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(orderDetailService.findById(id));
    }

}

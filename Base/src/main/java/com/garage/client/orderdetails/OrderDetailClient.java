package com.garage.client.orderdetails;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("order-details")
public interface OrderDetailClient {
    @PostMapping("/order-detail")
    OrderDetailResponse submitOrderDetail(@RequestBody OrderDetailRequest orderDetail);
    @GetMapping("/order-detail")
    List<OrderDetailResponse> getAllOrderDetail();
    @GetMapping("/order-detail")
    List<OrderDetailResponse> getOrderDetailByOrderId(@RequestParam("orderId") String orderId);
}

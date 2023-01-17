package com.garage.orderdetails;

import com.garage.client.orderdetails.OrderDetailRequest;
import com.garage.client.orderdetails.OrderDetailResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    public OrderDetail saveOrder(OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = OrderDetail.builder()
                .orderId(orderDetailRequest.getOrderId())
                .partsId(orderDetailRequest.getPartsId())
                .quantity(orderDetailRequest.getQuantity())
                .build();

        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetailResponse> findAll() {
        List<OrderDetail> orders = orderDetailRepository.findAll();

        log.info("orderDetailItem {}", orders.size());

        List<OrderDetailResponse> orderDetailResponses = orders.stream()
                .map(orderDetail -> OrderDetailResponse.builder()
                        .id(orderDetail.getId().toString())
                        .orderId(orderDetail.getOrderId())
                        .partsId(orderDetail.getPartsId())
                        .quantity(orderDetail.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return orderDetailResponses;
    }

    public List<OrderDetailResponse> findById(UUID id) {
        List<OrderDetail> orders = orderDetailRepository.findById(id).stream().collect(Collectors.toList());

        log.info("orderDetailItem {}", orders.size());

        List<OrderDetailResponse> orderDetailResponses = orders.stream()
                .map(orderDetail -> OrderDetailResponse.builder()
                        .id(orderDetail.getId().toString())
                        .orderId(orderDetail.getOrderId())
                        .partsId(orderDetail.getPartsId())
                        .quantity(orderDetail.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return orderDetailResponses;
    }

    public List<OrderDetailResponse> findByOrderId(String orderId) {
        List<OrderDetail> orders = orderDetailRepository.findByOrderIdIs(orderId);

        log.info("orderDetailItem {}", orders.size());

        List<OrderDetailResponse> orderDetailResponses = orders.stream()
                .map(orderDetail -> OrderDetailResponse.builder()
                        .id(orderDetail.getId().toString())
                        .orderId(orderDetail.getOrderId())
                        .partsId(orderDetail.getPartsId())
                        .quantity(orderDetail.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return orderDetailResponses;
    }
}

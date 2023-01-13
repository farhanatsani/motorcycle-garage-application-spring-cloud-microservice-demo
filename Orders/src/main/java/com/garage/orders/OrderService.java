package com.garage.orders;

import com.garage.client.orderdetails.OrderDetailClient;
import com.garage.client.orderdetails.OrderDetailRequest;
import com.garage.client.orderdetails.OrderDetailResponse;
import com.garage.client.parts.PartsClient;
import com.garage.client.parts.PartsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderDetailClient orderDetailClient;
    private final PartsClient partsClient;
    private final OrderRepository orderRepository;
    public Order createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .invoiceNumber(getRandomInvoiceNumber())
                .mechanicId(orderRequest.getMechanicId())
                .plateNumber(orderRequest.getPlateNumber())
                .date(LocalDate.now())
                .build();

        orderRepository.saveAndFlush(order);

        List<OrderDetailResponse> detailResponses = new ArrayList<>();
        for(OrderDetailRequest detailRequest: orderRequest.getOrderDetail()) {
            OrderDetailRequest orderDetail = OrderDetailRequest.builder()
                    .orderId(order.getId().toString())
                    .partsId(detailRequest.getPartsId())
                    .quantity(detailRequest.getQuantity())
                    .build();

            detailResponses.add(
                    orderDetailClient.submitOrderDetail(orderDetail)
            );
        }

        return order;
    }
    private String getRandomInvoiceNumber() {
        long random = (long) (Math.random() * 123456);
        String sequence = System.currentTimeMillis() + String.valueOf(random);
        return "ORD" + sequence.substring(sequence.length() - 6);
    }

    public List<OrderResponse> findAll() {
        List<Order> orders = orderRepository.findAll();

        List<OrderDetailResponse> orderDetail = getOrderDetail(orders.stream().findAny().get().getId().toString());

        // Get price from parts
        Long totalPrices = orderDetail.stream().mapToLong(OrderDetailResponse::getQuantity).sum();

        List<OrderResponse> orderResponses = orders
                .stream()
                .map(order ->
                        OrderResponse.builder()
                                .id(order.getId().toString())
                                .plateNumber(order.getPlateNumber())
                                .date(order.getDate())
                                .invoiceNumber(order.getInvoiceNumber())
                                .mechanicId(order.getMechanicId())
                                .orderDetail(orderDetail)
                                .totalPrices(new BigDecimal(totalPrices))
                                .build())
                .collect(Collectors.toList());
        return orderResponses;
    }

    public List<OrderResponse> findByOrderId(UUID id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        Order order = orderOptional.get();
        List<OrderDetailResponse> orderDetail = getOrderDetail(order.getId().toString());
        log.info("TEST {}", orderDetail.size());

        List<OrderResponse> orderResponses = Arrays.asList(
                OrderResponse.builder()
                        .id(order.getId().toString())
                        .plateNumber(order.getPlateNumber())
                        .date(order.getDate())
                        .invoiceNumber(order.getInvoiceNumber())
                        .mechanicId(order.getMechanicId())
                        .orderDetail(orderDetail)
                        .totalPrices(getTotalPrices(orderDetail))
                        .build()
        );

        return orderResponses;
    }

    public List<OrderDetailResponse> getOrderDetail(String id) {
        List<OrderDetailResponse> orderDetails = orderDetailClient
                .getOrderDetailByOrderId(id);
        return orderDetails;
    }

    public BigDecimal getTotalPrices(List<OrderDetailResponse> detailResponses) {
        Long totalPrices = 0L;
        for(OrderDetailResponse detailResponse: detailResponses) {
            PartsResponse partsResponse = partsClient.getParts(UUID.fromString(detailResponse.getPartsId()))
                    .stream().findAny().get();

            log.info("PartsResponse {}", partsResponse);

            totalPrices += (detailResponse.getQuantity() * partsResponse.getPrice().longValue());
        }
        return new BigDecimal(totalPrices);
    }
}

package com.garage.orderdetails.orders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public Order createOrder(RequestOrder requestOrder) {
        Order order = Order.builder()
                .invoiceNumber(getRandomInvoiceNumber())
                .mechanicId(requestOrder.getMechanicId())
                .plateNumber(requestOrder.getPlateNumber())
                .date(LocalDate.now())
                .build();
        return orderRepository.save(order);
    }
    private String getRandomInvoiceNumber() {
        long random = (long) (Math.random() * 123456);
        String sequence = System.currentTimeMillis() + String.valueOf(random);
        return "ORD" + sequence.substring(sequence.length() - 6);
    }
}

package com.raghumicroservices.order.service;

import com.raghumicroservices.order.dto.OrderRequest;
import com.raghumicroservices.order.model.Order;
import com.raghumicroservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest request){
        Order order=Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(request.skuCode())
                .price(request.price())
                .quantity(request.quantity())
                .build();
        orderRepository.save(order);
        log.info("Order placed");
    }
}

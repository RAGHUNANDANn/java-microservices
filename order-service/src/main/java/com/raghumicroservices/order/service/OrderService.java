package com.raghumicroservices.order.service;

import com.raghumicroservices.order.client.InventoryClient;
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
    private final InventoryClient inventoryClient;


    public void placeOrder(OrderRequest request){

       boolean isProductInStock= inventoryClient.isInStock(request.skuCode(), request.quantity());
if(isProductInStock){
    Order order=Order.builder()
            .orderNumber(UUID.randomUUID().toString())
            .skuCode(request.skuCode())
            .price(request.price())
            .quantity(request.quantity())
            .build();
    orderRepository.save(order);
    log.info("Order placed");
}else {
    log.info("Order not in stock");
    throw new RuntimeException("Product with SKUCODE"+request.skuCode()+"is  not in stock");
}
    }
}

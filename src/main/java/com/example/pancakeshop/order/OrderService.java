package com.example.pancakeshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepositoryd){
        this.orderRepository = orderRepositoryd;
    }


    public void createNewOrder(Order order) {
        LocalTime currentTime = LocalTime.now();
        order.setTime(currentTime);
        orderRepository.save(order);
    }
}

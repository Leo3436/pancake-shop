package com.example.pancakeshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepositoryd){
        this.orderRepository = orderRepositoryd;
    }


}

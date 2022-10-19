package com.example.pancakeshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    //Creates a new order by passing a description. Time is added automatically and
    //pancakes are addes to the order individually with a Post method
    @PostMapping
    public void createNewOrder (
            @RequestBody Order order){
        orderService.createNewOrder(order);
    }


    //returns a list of all orders present in the database
    @GetMapping
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
}

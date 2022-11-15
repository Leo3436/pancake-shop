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
    //pancakes are adds to the order individually with a Post method
    @PostMapping
    public Order createNewOrder (
            @RequestBody Order order){
        return orderService.createNewOrder(order);
    }


    //returns a list of all orders present in the database
    @GetMapping
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    //Add a pancake to an order
    @PutMapping("/{orderId}/add/pancake/{pancakeId}")
    public Order addToOrder(
            @PathVariable Long orderId,
            @PathVariable Long pancakeId){
        return orderService.addPancakeToOrder(orderId, pancakeId);
    }

    //Remove a pancake from an order
    @DeleteMapping("/{orderId}/remove/pancake/{pancakeId}")
    public Order removeFromOrder(
            @PathVariable Long orderId,
            @PathVariable Long pancakeId){
        return orderService.removePancakeFromOrder(orderId, pancakeId);
    }
}

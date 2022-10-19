package com.example.pancakeshop.order;

import com.example.pancakeshop.pancake.Pancake;
import com.example.pancakeshop.pancake.PancakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepositoryd){
        this.orderRepository = orderRepositoryd;
    }

    @Autowired
    private PancakeRepository pancakeRepository;


    public void createNewOrder(Order order) {
        LocalTime currentTime = LocalTime.now();
        order.setTime(currentTime);
        orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void addPancakeToOrder(Long orderId, Long pancakeId) {

        Set<Pancake> pancakeSet = null;

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException(
                        "Order with id " + orderId + " does not exist."));

        Pancake pancake = pancakeRepository.findById(pancakeId)
                .orElseThrow(() -> new IllegalStateException(
                        "Pancake with id " + pancakeId + " does not exist."));

        pancakeSet = order.getPancakesInOrder();
        pancakeSet.add(pancake);
        order.setPancakesInOrder(pancakeSet);
        orderRepository.save(order);

    }

    public void removePancakeFromOrder(Long orderId, Long pancakeId) {

        Set<Pancake> pancakeSet = null;

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException(
                        "Order with id " + orderId + " does not exist."));

        Pancake pancake = pancakeRepository.findById(pancakeId)
                .orElseThrow(() -> new IllegalStateException(
                        "Pancake with id " + pancakeId + " does not exist."));

        pancakeSet = order.getPancakesInOrder();
        if(pancakeSet.contains(pancake)){
            pancakeSet.remove(pancake);
            order.setPancakesInOrder(pancakeSet);
            orderRepository.save(order);
        }else{
            throw new IllegalStateException("Pancake with id " + pancakeId + " is not in order with id " + orderId);
        }
    }
}

package com.example.pancakeshop.order;

import com.example.pancakeshop.ingredient.Ingredient;
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

        //check if pancake is valid?
        Set<Ingredient>  ingredients = pancake.getIngredientsInPancake();
        int bases = 0;
        int stuffings = 0;
        for(Ingredient ingredient :  ingredients){
            if(ingredient.getType().equals("base")){
                bases++;
            }
            if(ingredient.getType().equals("stuffing")){
                stuffings++;
            }
        }

        //if the new pancake has 0 or more than one bases it's not valid and the exception is thrown
        //if the new pancake has less than 1 stuffing it's not valid and the exception is thrown
        //otherwise it's valid and it's added to the order
        if(bases != 1 || stuffings < 1){
            throw new IllegalStateException("A valid pancake has to have exactly one base and at least one stuffing. This one has " + bases + " bases and " + stuffings + " stuffings.");
        }else {

            Long oldPrice;
            if(order.getTotalPrice() == null){
                oldPrice = 0L;
            }else{
                oldPrice = order.getTotalPrice();   //price of the order before the new pancake is added
            }

            Long priceOfNewPancake = 0L;

            for(Ingredient ingredient : ingredients){
                priceOfNewPancake += Long.valueOf(ingredient.getPrice());    //calculates price of the new pancake
            }

            Long newFinalPrice = oldPrice + priceOfNewPancake;            //sums the old order price and the new pancake price to obtain the new total order price

            pancakeSet = order.getPancakesInOrder();
            pancakeSet.add(pancake);
            order.setPancakesInOrder(pancakeSet);
            order.setTotalPrice(newFinalPrice);
            orderRepository.save(order);
        }
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
        if (pancakeSet.contains(pancake)) {
            pancakeSet.remove(pancake);

            Long oldPrice = order.getTotalPrice();
            Long priceOfRemovedPancake = 0L;
            Set<Ingredient>  ingredients = pancake.getIngredientsInPancake();

            for(Ingredient ingredient : ingredients){
                priceOfRemovedPancake += Long.valueOf(ingredient.getPrice());
            }
            Long newPrice = oldPrice - priceOfRemovedPancake;

            order.setTotalPrice(newPrice);

            order.setPancakesInOrder(pancakeSet);
            orderRepository.save(order);
        } else {
            throw new IllegalStateException("Pancake with id " + pancakeId + " is not in order with id " + orderId);
        }
    }
}

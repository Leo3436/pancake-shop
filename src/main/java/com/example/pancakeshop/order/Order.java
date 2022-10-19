package com.example.pancakeshop.order;

import com.example.pancakeshop.pancake.Pancake;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "placement_time")
    private LocalTime time;
    @Column(name = "total_price")
    private Long totalPrice;

    @OneToMany
    private Set<Pancake> pancakesInOrder= new HashSet<>();


    public Order() {
    }

    public Order(Long id, String description, LocalTime time, Long totalPrice, Set<Pancake> pancakesInOrder) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.totalPrice = totalPrice;
        this.pancakesInOrder = pancakesInOrder;
    }

    public Order(String description, LocalTime time, Long totalPrice, Set<Pancake> pancakesInOrder) {
        this.description = description;
        this.time = time;
        this.totalPrice = totalPrice;
        this.pancakesInOrder = pancakesInOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Pancake> getPancakesInOrder() {
        return pancakesInOrder;
    }

    public void setPancakesInOrder(Set<Pancake> pancakesInOrder) {
        this.pancakesInOrder = pancakesInOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", totalPrice=" + totalPrice +
                ", pancakesInOrder=" + pancakesInOrder +
                '}';
    }
}

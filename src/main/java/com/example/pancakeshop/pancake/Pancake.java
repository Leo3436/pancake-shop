package com.example.pancakeshop.pancake;

import com.example.pancakeshop.ingredient.Ingredient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pancakes")
public class Pancake {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "order_id")
    private Integer order;

    @ManyToMany
    @JoinTable(
            name="madeof",
            joinColumns = @JoinColumn(name = "pancake_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Ingredient> madeOf = new HashSet<>();

    public Pancake(Long id) {
        this.id = id;
    }

    public Pancake(Long id, Set<Ingredient> ingredients) {
        this.id = id;
        this.madeOf = ingredients;
    }

    public Pancake() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Set<Ingredient> getIngredientsInPancake() {
        return madeOf;
    }

    public void setIngredientsInPancake(Set<Ingredient> ingredients) {
        this.madeOf = ingredients;
    }

    private class Order {
    }

    @Override
    public String toString() {
        return "Pancake{" +
                "id=" + id +
                ", order=" + order +
                ", madeOf=" + madeOf +
                '}';
    }
}

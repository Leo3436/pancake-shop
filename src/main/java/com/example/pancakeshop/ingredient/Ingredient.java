package com.example.pancakeshop.ingredient;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Ingredient {
    private Long id;
    private String name;
    private Long price;
    private boolean isHealthy;
    private IngredientType type;

    public enum IngredientType{
        BASE,
        STUFFING,
        TOPPING,
        FRUIT
    }



}

package com.example.pancakeshop.ingredient;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ing_name")
    private String name;
    @Column(name = "price")
    private Integer price;
    @Column(name = "healthy")
    private Boolean isHealthy;
    @Column(name = "type")
    private String type;


    //CONSTRUCTOR with ID
    public Ingredient(Long id, String name, Integer price, Boolean isHealthy, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isHealthy = isHealthy;
        this.type = type;
    }

    //CONSTRUCTOR without ID
    public Ingredient(String name, Integer price, Boolean isHealthy, String type) {
        this.name = name;
        this.price = price;
        this.isHealthy = isHealthy;
        this.type = type;
    }

    //CONSTRUCTOR with no parameters
    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(Boolean healthy) {
        isHealthy = healthy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isHealthy=" + isHealthy +
                ", type=" + type +
                '}';
    }
}

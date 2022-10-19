package com.example.pancakeshop.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path =  "api/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    //Returns a list of all ingredients in the database
    @GetMapping
    public List<Ingredient> getIngredients(){
        return ingredientService.getIngredients();
    }

    //Insert a new ingredient to the database
    @PostMapping
    public void addNewIngredient(@RequestBody Ingredient ingredient){
        ingredientService.addNewIngredient(ingredient);
    }

    //Delete an ingredient by Id
    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(
            @PathVariable("ingredientId") Long ingredientId){
        ingredientService.deleteIngredient(ingredientId);
    }

    //Update an ingredient's name and/or price and/or type
    @PutMapping("/{ingredientId}")
    public void updateIngredient(
            @PathVariable("ingredientId") Long ingredientId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) String type){
        ingredientService.updateIngredient(ingredientId, name, price, type);
    }

}

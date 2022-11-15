package com.example.pancakeshop.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Ingredient addNewIngredient(@RequestBody Ingredient ingredient){
        return ingredientService.addNewIngredient(ingredient);
    }

    //Delete an ingredient by Id
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<String> deleteIngredient(
            @PathVariable("ingredientId") Long ingredientId){
        ingredientService.deleteIngredient(ingredientId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Ingredient deleted.");
    }

    //Update an ingredient's name and/or price and/or type
    @PutMapping("/{ingredientId}")
    public Ingredient updateIngredient(
            @PathVariable("ingredientId") Long ingredientId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) String type){
        return ingredientService.updateIngredient(ingredientId, name, price, type);
    }

}

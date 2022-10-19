package com.example.pancakeshop.pancake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pancake")
public class PancakeController {
    private final PancakeService pancakeService;

    @Autowired
    public PancakeController(PancakeService pancakeService) {
        this.pancakeService = pancakeService;
    }

    //Returns a list of all pancakes in the database
    @GetMapping
    public List<Pancake> getPancakes(){
        return pancakeService.getPancakes();
    }

     @GetMapping("/{pancakeId}")
     public Pancake getPancake(
             @PathVariable("pancakeId") Long pancakeId){
        return pancakeService.getPancake(pancakeId);
     }

    //Insert a new pancake to the database
    @PostMapping
    public void addNewPancake(@RequestBody Pancake pancake){
        pancakeService.addNewPancake(pancake);
    }

    //Delete a pancake by Id
    @DeleteMapping("/{pancakeId}")
    public void deletePancake(
            @PathVariable("pancakeId") Long pancakeId){
        pancakeService.deletePancake(pancakeId);
    }

    //Add an ingredient to a pancake
    @PutMapping("/{pancakeId}/add/ingredient/{ingredientId}")
    public void addToPancake(
            @PathVariable Long pancakeId,
            @PathVariable Long ingredientId){
        pancakeService.addIngredientToPancake(pancakeId, ingredientId);
    }

    //Remove an ingredient from a pancake
    @DeleteMapping("/{pancakeId}/remove/ingredient/{ingredientId}")
    public void removeFromPancake(
            @PathVariable Long pancakeId,
            @PathVariable Long ingredientId){
        pancakeService.removeIngredientFrompancake(pancakeId, ingredientId);
    }
}

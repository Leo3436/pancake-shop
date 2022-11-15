package com.example.pancakeshop.pancake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public Pancake addNewPancake(
            @RequestBody Pancake pancake){
        return pancakeService.addNewPancake(pancake);
    }

    @PostMapping(path = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void addPancakeWithIngredients(@RequestBody Pancake pancake) {
        pancakeService.addNewPancake(pancake);
    }

    //Delete a pancake by Id
    @DeleteMapping("/{pancakeId}")
    public ResponseEntity<String> deletePancake(
            @PathVariable("pancakeId") Long pancakeId){
        pancakeService.deletePancake(pancakeId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Pancake deleted.");
    }

    //Add an ingredient to a pancake
    @PutMapping("/{pancakeId}/add/ingredient/{ingredientId}")
    public Pancake addToPancake(
            @PathVariable Long pancakeId,
            @PathVariable Long ingredientId){
        return pancakeService.addIngredientToPancake(pancakeId, ingredientId);
    }

    //Remove an ingredient from a pancake
    @DeleteMapping("/{pancakeId}/remove/ingredient/{ingredientId}")
    public Pancake removeFromPancake(
            @PathVariable Long pancakeId,
            @PathVariable Long ingredientId){
        return pancakeService.removeIngredientFromPancake(pancakeId, ingredientId);
    }

}

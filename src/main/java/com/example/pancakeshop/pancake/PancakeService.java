package com.example.pancakeshop.pancake;

import com.example.pancakeshop.ingredient.Ingredient;
import com.example.pancakeshop.ingredient.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PancakeService {
    private final PancakeRepository pancakeRepository;

    @Autowired
    public PancakeService(PancakeRepository pancakeRepository){
        this.pancakeRepository = pancakeRepository;
    }

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientRepository IngredientService(IngredientRepository ingredientRepository){
        return ingredientRepository;
    }


    public List<Pancake> getPancakes() {
        return pancakeRepository.findAll();
    }

    public void deletePancake(Long pancakeId) {
        boolean exists = pancakeRepository.existsById(pancakeId);
        if(!exists){
            throw new IllegalStateException("Pancake with id " + pancakeId + " does not exist.");
        }
        pancakeRepository.deleteById(pancakeId);
    }

    public void addNewPancake(Pancake pancake) {
        pancakeRepository.save(pancake);
    }

    public void addIngredientToPancake(Long pancakeId, Long ingredientId) {

        Set<Ingredient> ingredientSet = null;

        Pancake pancake = pancakeRepository.findById(pancakeId)
                .orElseThrow(() -> new IllegalStateException(
                        "Pancake with id " + pancakeId + " does not exist."));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalStateException(
                        "Ingredient with id " + ingredientId + " does not exist."));

        ingredientSet = pancake.getIngredientsInPancake();
        ingredientSet.add(ingredient);
        pancake.setIngredientsInPancake(ingredientSet);
        pancakeRepository.save(pancake);
    }


    public void removeIngredientFrompancake(Long pancakeId, Long ingredientId) {

        Set<Ingredient> ingredientSet = null;

        Pancake pancake = pancakeRepository.findById(pancakeId)
                .orElseThrow(() -> new IllegalStateException(
                        "Pancake with id " + pancakeId + " does not exist."));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalStateException(
                        "Ingredient with id " + ingredientId + " does not exist."));

        ingredientSet = pancake.getIngredientsInPancake();
        if(ingredientSet.contains(ingredient)){
            ingredientSet.remove(ingredient);
            pancake.setIngredientsInPancake(ingredientSet);
            pancakeRepository.save(pancake);
        }else{
            throw new IllegalStateException("Ingredient with id " + ingredientId + " is not in pancake with id " + pancakeId);
        }

    }


    public Pancake getPancake(Long pancakeId) {
        Optional<Pancake> optionalPancake = pancakeRepository.findById(pancakeId);
        Pancake pancake = optionalPancake.get();
        return pancake;
    }
}

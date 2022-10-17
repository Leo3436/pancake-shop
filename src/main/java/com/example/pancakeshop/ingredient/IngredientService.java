package com.example.pancakeshop.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public void addNewIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingredientOptional = ingredientRepository
                .findIngredientByName(ingredient.getName());

        if(ingredientOptional.isPresent()){
            throw new IllegalStateException("Ingredient with that name already exists.");
        }

        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long ingredientId){
        boolean exists = ingredientRepository.existsById(ingredientId);
        if(!exists){
            throw new IllegalStateException("Ingredient with id " + ingredientId + " does not exist.");
        }
        ingredientRepository.deleteById(ingredientId);
    }

    @Transactional
    public void updateIngredient(Long ingredientId, String name, Integer price, String type) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalStateException(
                        "Ingredient with id " + ingredientId + " does not exist."));

        if(name != null && name.length() > 0 && !Objects.equals(ingredient.getName(), name)){
            ingredient.setName(name);
        }

        if(price != null && price > 0 && !Objects.equals(ingredient.getPrice(), price)){
            ingredient.setPrice(price);
        }

        if(type != null && type.length() > 0 && !Objects.equals(ingredient.getType(), type)){
            ingredient.setType(type);
        }
    }
}

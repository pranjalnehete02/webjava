package com.example.recipes.service;

import com.example.recipes.model.Recipe;
import com.example.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long recipeId, Recipe updatedRecipe) {
        Recipe existingRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        existingRecipe.setTitle(updatedRecipe.getTitle());
        existingRecipe.setDescription(updatedRecipe.getDescription());
        existingRecipe.setIngredients(updatedRecipe.getIngredients());
        existingRecipe.setInstructions(updatedRecipe.getInstructions());
        existingRecipe.setDifficultyLevel(updatedRecipe.getDifficultyLevel());
        existingRecipe.setCuisineType(updatedRecipe.getCuisineType());

        return recipeRepository.save(existingRecipe);
    }

    public void deleteRecipe(Long recipeId) {
        Recipe existingRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        recipeRepository.delete(existingRecipe);
    }
}

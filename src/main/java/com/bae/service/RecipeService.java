package com.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.exceptions.RecipeNotFound;
import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;

@Service
public class RecipeService {

	private RecipeRepo recRepo;

	public RecipeService(RecipeRepo recRepo) {
		this.recRepo = recRepo;
	}
	
	
	public List<Recipe> getAllRecipes()
	{
		return recRepo.findAll();
	}
	
	public Recipe findRecipeById(int id) {
		return this.recRepo.findById(id).orElseThrow(
				() -> new RecipeNotFound());
	}
	

	public void deleteRecipeByID(int recipeId) 
	{
		recRepo.deleteById(recipeId);
	}
	
	public Recipe createRecipe(Recipe recipeToAdd) 
	{
		return recRepo.save(recipeToAdd);
	}
	
	public Recipe updateRecipe(Recipe recipe, int id) {
		Recipe recipeToUpdate = findRecipeById(id);
		recipeToUpdate.setMethod(recipe.getMethod());
		recipeToUpdate.setRating(recipe.getRating());
		recipeToUpdate.setRecipeName(recipe.getRecipeName());
		recipeToUpdate.setServingAmount(recipe.getServingAmount());
		recipeToUpdate.setTimeToMake(recipe.getTimeToMake());
		return this.recRepo.save(recipeToUpdate);
	}
	
}

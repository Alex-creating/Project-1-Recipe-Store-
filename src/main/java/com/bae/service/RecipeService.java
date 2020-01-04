package com.bae.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bae.exceptions.InvalidEntryException;
import com.bae.exceptions.RecipeNotFound;
import com.bae.persistence.domain.Category;
import com.bae.persistence.domain.Ingredients;
import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;

@Service
public class RecipeService {

	private RecipeRepo recRepo;

	
	String regex = "^[a-zA-Z0-9 ]+$";
	Pattern pattern = Pattern.compile(regex);
	
	public boolean specialCharacterChecker(String checkedName) {
		Matcher matcher = pattern.matcher(checkedName);
		return matcher.matches();
	}
	

	public RecipeService(RecipeRepo recRepo) {
		this.recRepo = recRepo;
	}
	
	
	public List<Recipe> getAllRecipes()
	{
		return recRepo.findAll();
	}
	
	public Recipe findRecipeById(int id) {
		return this.recRepo.findById(id).orElseThrow(RecipeNotFound::new);
	}
	

	public void deleteRecipeById(int id) 
	{
		recRepo.deleteById(id);
	}
	public Recipe addIngredientToRecipe(int id, Ingredients ingredient) {
		Recipe recipeToUpdate = this.findRecipeById(id);
		recipeToUpdate.getIngredients().add(ingredient);
		return this.recRepo.saveAndFlush(recipeToUpdate);
	}
	public Recipe addCategoryToRecipe(int id, Category category) {
		Recipe recipeToUpdate = this.findRecipeById(id);
		recipeToUpdate.getCategories().add(category);
		return this.recRepo.saveAndFlush(recipeToUpdate);
	}
	
	public Recipe createRecipe(Recipe recipeToAdd) 
	{
		if (recipeToAdd.getRecipeName().length()<3 || recipeToAdd.getRecipeName().length()>50 ) {
			throw new InvalidEntryException();
		}
		if ( recipeToAdd.getRating() == 0 || recipeToAdd.getRating() > 5) {
			throw new InvalidEntryException();
		}
		if (recipeToAdd.getTimeToMake() == 0 || recipeToAdd.getTimeToMake() > 600) {
			throw new InvalidEntryException();
		}
		if (recipeToAdd.getServingAmount() == 0) {
			throw new InvalidEntryException();
		}
		if (specialCharacterChecker(recipeToAdd.getRecipeName()) == false) {
			throw new InvalidEntryException();
		}
		if (StringUtils.isNumeric(recipeToAdd.getRecipeName())) {
			throw new InvalidEntryException();
		}
		
			return recRepo.save(recipeToAdd);
	}
	
	public Recipe updateRecipe(Recipe recipe, int id) {
		Recipe recipeToUpdate = findRecipeById(id);
		recipeToUpdate.setMethod(recipe.getMethod());
		recipeToUpdate.setRating(recipe.getRating());
		recipeToUpdate.setRecipeName(recipe.getRecipeName());
		recipeToUpdate.setServingAmount(recipe.getServingAmount());
		recipeToUpdate.setTimeToMake(recipe.getTimeToMake());
		
		if (recipeToUpdate.getRecipeName().length()<3 || recipeToUpdate.getRecipeName().length()>50 ) {
			throw new InvalidEntryException();
		}
		if ( recipeToUpdate.getRating() == 0 || recipeToUpdate.getRating() > 5) {
			throw new InvalidEntryException();
		}
		if (recipeToUpdate.getTimeToMake() == 0 || recipeToUpdate.getTimeToMake() > 600) {
			throw new InvalidEntryException();
		}
		if (recipeToUpdate.getServingAmount() == 0) {
			throw new InvalidEntryException();
		}
		if (specialCharacterChecker(recipeToUpdate.getRecipeName()) == false) {
			throw new InvalidEntryException();
		}
		if (StringUtils.isNumeric(recipeToUpdate.getRecipeName())) {
			throw new InvalidEntryException();
		}
	
		return this.recRepo.save(recipeToUpdate);
	}
	
}

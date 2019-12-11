package com.bae.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.persistence.domain.Recipe;
import com.bae.service.RecipeService;

@RestController
public class RecipeController {
	
	private RecipeService recService;
	
	@Autowired
	public RecipeController (RecipeService recService) {
		super();
		 this.recService = recService;
	}
	
	@PostMapping("/createRecipe")
	public Recipe createRecipe(@RequestBody Recipe recipe) {
	return this.recService.createRecipe(recipe);
	}
	
	@DeleteMapping("/deleteRecipe/{recipeName}")
	public void deleteDuck(@PathVariable Recipe recipeName) {
		this.recService.deleteRecipeByName(recipeName);
	}
}

package com.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Category;
import com.bae.persistence.domain.Ingredients;
import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.CategoryRepo;
import com.bae.persistence.repo.IngredientsRepo;
import com.bae.persistence.repo.RecipeRepo;
import com.bae.service.RecipeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class RecipeServiceIntegrationTest {
	
	@Autowired
	private RecipeService recService;
	
	@Autowired
	private RecipeRepo recRepo;
	
	@Autowired
	private IngredientsRepo ingRepo;
	
	@Autowired
	private CategoryRepo catRepo;
	
	private Recipe testRecipe;
	private Recipe testRecipeWithId;
	private Recipe testRecipeWithCategories;
	private Recipe testRecipeWithIngredients;
	
	private Ingredients testIng;
	private Ingredients testIngWithId;
	
	private Category testCat;
	private Category testCatWithId;
	
	@Before
	public void init() {
		this.testRecipe = new Recipe("Lasagna", "Cook", 5, 120, 3);
		this.testIng = new Ingredients("Pasta");
		this.testCat = new Category("Meat");
		
		this.recRepo.deleteAll();
		this.ingRepo.deleteAll();
		this.catRepo.deleteAll();
		
		this.testRecipeWithId = this.recRepo.save(this.testRecipe);
		this.testIngWithId = this.ingRepo.save(this.testIng);
		this.testCatWithId = this.catRepo.save(this.testCat);
		
		this.testRecipeWithCategories = this.recRepo.save(this.testRecipe);
		this.testRecipeWithIngredients = this.recRepo.save(this.testRecipe);
		

	}

	@Test
	public void testGetAllRecipes() {
		assertThat(this.recService.getAllRecipes()).isEqualTo(Arrays.asList(new Recipe[] { this.testRecipeWithId}));
	}
	
	@Test
	public void testCreateRecipe() {
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));
	}

	@Test
	public void testDeleteRecipeById() {
		assertThat(this.recService.deleteRecipeById(this.testRecipeWithId.getRecipeId())).isFalse();
	}

	@Test
	public void testFindRecipeByID() {
		assertThat(this.recService.findRecipeById(this.testRecipeWithId.getRecipeId())).isEqualTo(this.testRecipeWithId);
	}

	@Test
	public void testUpdateRecipe() {
		Recipe newRecipe = new Recipe("Bolognese", "Cook", 5, 120, 3);
		Recipe updatedRecipe = new Recipe(newRecipe.getRecipeName(), newRecipe.getMethod(), newRecipe.getRating(), newRecipe.getTimeToMake(), newRecipe.getServingAmount());
		updatedRecipe.setRecipeId(this.testRecipeWithId.getRecipeId());

		assertThat(this.recService.updateRecipe(newRecipe, this.testRecipeWithId.getRecipeId())).isEqualTo(updatedRecipe);
	
}
	
	@Test
	public void testUpdateRecipeWithCategories() {
		
		Set<Category> categoryToAdd = new HashSet<>();
		categoryToAdd.add(testCatWithId);
		
		this.testRecipeWithCategories.getCategories().addAll(categoryToAdd);
		
		assertEquals(testRecipeWithCategories, this.recService.updateRecipeWithCategories(this.testRecipeWithId.getRecipeId(), categoryToAdd));
	}
	
	@Test
	public void testUpdateRecipeWithIngredients() {
		
		Set<Ingredients> ingredientToAdd = new HashSet<>();
		ingredientToAdd.add(testIngWithId);
		
		this.testRecipeWithIngredients.getIngredients().addAll((ingredientToAdd));
		
		assertEquals(testRecipeWithIngredients, this.recService.updateRecipeWithIngredients(this.testRecipeWithId.getRecipeId(), ingredientToAdd));
	}
}

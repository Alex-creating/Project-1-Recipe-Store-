package com.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;
import com.bae.service.RecipeService;

@RunWith(SpringRunner.class)
public class RecipeServiceUnitTest {
	
	@InjectMocks
	private RecipeService recService;
	
	@Mock
	private RecipeRepo recRepo;
	
	private List<Recipe> recipeList;
	private Recipe testRecipe;
	private Recipe testRecipeWithId;
	
	final int id = 1;
	
	@Before
	public void init() {
		this.recipeList = new ArrayList<>();
		this.recipeList.add(testRecipe);
		this.testRecipe = new Recipe ("Lasagna", "Cook", 5, 120, 3);
		this.testRecipeWithId = new Recipe (testRecipe.getRecipeName(), testRecipe.getMethod(), testRecipe.getRating(), testRecipe.getTimeToMake(), testRecipe.getServingAmount());
		this.testRecipeWithId.setRecipeId(id);		
	}
	
	@Test
	public void createRecipeTest() {
		when(this.recRepo.save(testRecipe)).thenReturn(testRecipeWithId);
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));
		verify(this.recRepo, times(1)).save(this.testRecipe);
	}
	
	@Test
	public void deleteRecipeTest() {
		when(this.recRepo.existsById(1)).thenReturn(true, false);
		this.recService.deleteRecipeById(1);
		
		verify(this.recRepo, times(1)).deleteById(1);
//		verify(this.recRepo, times(1)).findById(id);
	}
	
	@Test
	public void findRecipeByIdTest() {
		when(this.recRepo.findById(this.id)).thenReturn(Optional.of(this.testRecipeWithId));
		assertEquals(this.testRecipeWithId, this.recService.findRecipeById(this.id));
		verify(this.recRepo, times(1)).findById(this.id);
	}
	
	@Test
	public void findAllRecipesTest() {
		when(recRepo.findAll()).thenReturn(this.recipeList);
		assertFalse("There are no recipes", this.recService.getAllRecipes().isEmpty());
		verify(this.recRepo,times(1)).findAll();
	}
	
	@Test
	public void updateRecipeTest() { 
		Recipe newRecipe = new Recipe("Pizza", "Boil",2, 20,5);
		Recipe updatedRecipe = new Recipe (newRecipe.getRecipeName(), newRecipe.getMethod(), newRecipe.getRating(), newRecipe.getTimeToMake(), newRecipe.getServingAmount());
		updatedRecipe.setRecipeId(this.id);
		
		when(this.recRepo.findById(this.id)).thenReturn(Optional.of(this.testRecipeWithId));
		when(this.recRepo.save(updatedRecipe)).thenReturn(updatedRecipe);
		
		assertEquals(updatedRecipe, this.recService.updateRecipe(newRecipe, this.id));
		verify(this.recRepo, times(1)).findById(1);
		verify(this.recRepo, times(1)).save(updatedRecipe);
	}

}

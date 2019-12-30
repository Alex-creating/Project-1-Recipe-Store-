package com.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Recipe;
import com.bae.rest.RecipeController;
import com.bae.service.RecipeService;

@RunWith(SpringRunner.class)
public class RecipeControllerUnitTest {
	
	@InjectMocks
	private RecipeController controller;
	
	@Mock
	private RecipeService recService;
	
	private List<Recipe> recList;
	private Recipe testRec;
	private Recipe testRecWithId;
	final int id = 1;
	
	@Before
	public void init() {
		this.recList = new ArrayList<>();
		this.recList.add(testRec);
		this.testRec = new Recipe("Lasagna", "bake", 12, 12, 125);
		this.testRecWithId = new Recipe(testRec.getRecipeName(), testRec.getMethod(), testRec.getRating(), testRec.getTimeToMake(), testRec.getServingAmount());
		this.testRecWithId.setRecipeId(id);
	}
	
	@Test
	public void createRecipeTest() {
		when(this.recService.createRecipe(testRec)).thenReturn(testRecWithId);

		assertEquals(this.testRecWithId, this.controller.createRecipe(testRec));

		verify(this.recService, times(1)).createRecipe(this.testRec);
	}

	@Test
	public void deleteRecipeTest() {
		this.controller.deleteRecipeById(id);

		verify(this.recService, times(1)).deleteRecipeById(id);
	}

	@Test
	public void findRecipeByIdTest() {
		when(this.recService.findRecipeById(this.id)).thenReturn(this.testRecWithId);

		assertEquals(this.testRecWithId, this.controller.getRecipe(this.id));

		verify(this.recService, times(1)).findRecipeById(this.id);
	}

	@Test
	public void getAllRecipeTest() {

		when(recService.getAllRecipes()).thenReturn(this.recList);

		assertFalse("Controller has found no recipes", this.controller.getAllRecipes().isEmpty());

		verify(recService, times(1)).getAllRecipes();
	}

	@Test
	public void updateRecipeTest() {
		
		Recipe newRecipe = new Recipe("Bolognese", "Cook", 5, 120, 3);
		Recipe updatedRecipe = new Recipe(newRecipe.getRecipeName(), newRecipe.getMethod(), newRecipe.getRating(), newRecipe.getTimeToMake(), newRecipe.getServingAmount());
		updatedRecipe.setRecipeId(this.id);

		when(this.recService.updateRecipe(newRecipe, this.id)).thenReturn(updatedRecipe);

		assertEquals(updatedRecipe, this.controller.updateRecipe(this.id, newRecipe));

		verify(this.recService, times(1)).updateRecipe(newRecipe, this.id);
	}

}

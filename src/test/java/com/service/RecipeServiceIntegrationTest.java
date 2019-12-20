package com.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;
import com.bae.service.RecipeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class RecipeServiceIntegrationTest {
	
	@Autowired
	private RecipeService recService;
	
	@Autowired
	private RecipeRepo recRepo;
	
	private Recipe testRecipe;
	private Recipe testRecipeWithId;
	
	@Before
	public void init() {
		this.testRecipe = new Recipe("Lasagna", "Cook", 5, 120, 3);
		
		
		this.recRepo.deleteAll();
		this.testRecipeWithId = this.recRepo.save(this.testRecipe);
	}

	@Test
	public void testGetAllRecipes() {
		assertThat(this.recService.getAllRecipes()).isEqualTo(Arrays.asList(new Recipe[] { this.testRecipeWithId}));
	}
	
	@Test
	public void testCreateRecipe() {
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));
	}

//	@Test
//	public void testDeleteRecipeById() {
//		assertThat(this.recService.deleteRecipeById(1)).
//	}

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
}

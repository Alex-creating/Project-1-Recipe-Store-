package com.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Ingredients;
import com.bae.persistence.repo.IngredientsRepo;
import com.bae.service.IngredientsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class IngredientsServiceIntegrationTest {
	
	@Autowired
	private IngredientsService ingService;
	
	@Autowired
	private IngredientsRepo ingRepo;
	
	private Ingredients testIngredients;
	private Ingredients testIngredientsWithId;
	
	@Before
	public void init() {
		this.testIngredients = new Ingredients("tomatoes");
		this.testIngredientsWithId = testIngredients;
		
		this.ingRepo.deleteAll();
		this.testIngredientsWithId = this.ingRepo.save(this.testIngredients);
	}

	@Test
	public void testGetAllIngredients() {
		assertThat(this.ingService.getAllIngredients()).isEqualTo(Arrays.asList(new Ingredients[] { this.testIngredientsWithId}));
	}
	
	@Test
	public void testCreateIngredients() {
		assertEquals(this.testIngredientsWithId, this.ingService.createIngredient(testIngredients));
	}

//	@Test
//	public void testDeleteIngredientsById() {
//		assertThat(this.ingService.deleteIngredientsById(1)).
//	}

	@Test
	public void testFindIngredientsByID() {
		assertThat(this.ingService.findIngredientById(this.testIngredientsWithId.getIngredientId())).isEqualTo(this.testIngredientsWithId);
	}

	@Test
	public void testUpdateIngredients() {
		Ingredients newIngredients = new Ingredients("Ham");
		Ingredients updatedIngredients = new Ingredients(newIngredients.getIngredientName());
		updatedIngredients.setIngredientId(this.testIngredientsWithId.getIngredientId());

		assertThat(this.ingService.updateIngredients(newIngredients, this.testIngredientsWithId.getIngredientId())).isEqualTo(updatedIngredients);
	
}

}

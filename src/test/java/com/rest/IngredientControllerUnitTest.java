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

import com.bae.persistence.domain.Ingredients;
import com.bae.rest.IngredientsController;
import com.bae.service.IngredientsService;

@RunWith(SpringRunner.class)
public class IngredientControllerUnitTest {
	
	@InjectMocks
	private IngredientsController controller;
	
	@Mock
	private IngredientsService ingService;
	
	private List<Ingredients> ingList;
	private Ingredients testIng;
	private Ingredients testIngWithId;
	final int id = 1;
	
	@Before
	public void init() {
		this.ingList = new ArrayList<>();
		this.ingList.add(testIng);
		this.testIng = new Ingredients("Tomato");
		this.testIngWithId = new Ingredients(testIng.getIngredientName());
		this.testIngWithId.setIngredientId(id);
	}
	
	@Test
	public void createIngredientTest() {
		when(this.ingService.createIngredient(testIng)).thenReturn(testIngWithId);

		assertEquals(this.testIngWithId, this.controller.createIngredients(testIng));

		verify(this.ingService, times(1)).createIngredient(this.testIng);
	}

	@Test
	public void deleteIngredientTest() {
		this.controller.deleteIngredientById(id);

		verify(this.ingService, times(1)).deleteIngredientById(id);
	}

	@Test
	public void findIngredientByIdTest() {
		when(this.ingService.findIngredientById(this.id)).thenReturn(this.testIngWithId);

		assertEquals(this.testIngWithId, this.controller.getIngredient(this.id));

		verify(this.ingService, times(1)).findIngredientById(this.id);
	}

	@Test
	public void getAllIngredientTest() {

		when(ingService.getAllIngredients()).thenReturn(this.ingList);

		assertFalse("Controller has found no ingredients", this.controller.getAllIngredients().isEmpty());

		verify(ingService, times(1)).getAllIngredients();
	}

	@Test
	public void updateIngredientTest() {
		
		Ingredients newIngredient = new Ingredients("Potato");
		Ingredients updatedIngredient = new Ingredients(newIngredient.getIngredientName());
		updatedIngredient.setIngredientId(this.id);

		when(this.ingService.updateIngredients(newIngredient, this.id)).thenReturn(updatedIngredient);

		assertEquals(updatedIngredient, this.controller.updateIngredient(this.id, newIngredient));

		verify(this.ingService, times(1)).updateIngredients(newIngredient, this.id);
	}

}

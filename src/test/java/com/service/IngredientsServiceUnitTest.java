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

import com.bae.persistence.domain.Ingredients;
import com.bae.persistence.repo.IngredientsRepo;
import com.bae.service.IngredientsService;

@RunWith(SpringRunner.class)
public class IngredientsServiceUnitTest {
	
	@InjectMocks
	private IngredientsService ingService;

	@Mock
	private IngredientsRepo ingRepo;

	private List<Ingredients> ingList;

	private Ingredients testIngredient;

	private Ingredients testIngredientWithId;

	final int id = 1;
	
	@Before
	public void init() {
		this.ingList = new ArrayList<>();
		this.ingList.add(testIngredient);
		this.testIngredient = new Ingredients("Tomato");
		this.testIngredientWithId = new Ingredients(testIngredient.getIngredientName());
		this.testIngredientWithId.setIngredientId(id);
	}

	@Test
	public void createIngredientTest() {
		when(this.ingRepo.save(testIngredient)).thenReturn(testIngredientWithId);

		assertEquals(this.testIngredientWithId, this.ingService.createIngredient(testIngredient));

		verify(this.ingRepo, times(1)).save(this.testIngredient);
	}

	@Test
	public void deleteIngredientTest() {
		when(this.ingRepo.existsById(id)).thenReturn(true, false);

		this.ingService.deleteIngredientById(id);

		verify(this.ingRepo, times(1)).deleteById(id);
//		verify(this.ingRepo, times(2)).existsById(id);
	}

	@Test
	public void findIngredientByIdTest() {
		when(this.ingRepo.findById(this.id)).thenReturn(Optional.of(this.testIngredientWithId));

		assertEquals(this.testIngredientWithId, this.ingService.findIngredientById(this.id));

		verify(this.ingRepo, times(1)).findById(this.id);
	}

	@Test
	public void readIngredientsTest() {

		when(ingRepo.findAll()).thenReturn(this.ingList);

		assertFalse("Controller has found no ingredients", this.ingService.getAllIngredients().isEmpty());

		verify(ingRepo, times(1)).findAll();
	}

	@Test
	public void updateIngredientTest() {
		
		Ingredients newIngredient = new Ingredients("Potato");
		Ingredients updatedIngredient = new Ingredients(newIngredient.getIngredientName());
		updatedIngredient.setIngredientId(this.id);

		when(this.ingRepo.findById(this.id)).thenReturn(Optional.of(this.testIngredientWithId));
		when(this.ingRepo.save(updatedIngredient)).thenReturn(updatedIngredient);

		assertEquals(updatedIngredient, this.ingService.updateIngredients(newIngredient, this.id));

		verify(this.ingRepo, times(1)).findById(1);
		verify(this.ingRepo, times(1)).save(updatedIngredient);
	}

}

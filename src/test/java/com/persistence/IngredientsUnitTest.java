package com.persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Ingredients;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class IngredientsUnitTest {
	
	@Test
	public void hashcodeAndEqualsIngredientsTest() {
		Ingredients ingredient1 = new Ingredients("Tomato");
		Ingredients ingredient2 = new Ingredients("Tomato");
		
		assertTrue(ingredient1.equals(ingredient2) && ingredient2.equals(ingredient1));
		assertTrue(ingredient1.hashCode() == ingredient2.hashCode());
	}
	
	@Test
	public void failingEqualsAndHashcodeIngredientsTest() {
		Ingredients ingredient = new Ingredients("potato");
		Ingredients notTheSameIngredient = new Ingredients("tomato");
		
		assertFalse(ingredient.equals(notTheSameIngredient) && notTheSameIngredient.equals(ingredient));
		assertFalse(ingredient.hashCode() == notTheSameIngredient.hashCode());
	}

}

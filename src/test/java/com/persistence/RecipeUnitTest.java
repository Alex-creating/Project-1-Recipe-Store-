package com.persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Recipe;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class RecipeUnitTest {
	
	@Test
	public void hashcodeAndEqualsRecipeTest() {
		Recipe recipe1 = new Recipe("Lasagna", "Cook it", 5, 5, 5);
		Recipe recipe2 = new Recipe("Lasagna", "Cook it", 5, 5, 5);
		
		assertTrue(recipe1.equals(recipe2) && recipe2.equals(recipe1));
		assertTrue(recipe1.hashCode() == recipe2.hashCode());
	}
	
	@Test
	public void failingEqualsAndHashcodeCategoryTest() {
		Recipe recipe = new Recipe("Lasagna", "Cook it", 5, 5, 5);
		Recipe failingRecipe = new Recipe("Pie", "Cook it", 5, 5, 5);
		
		assertFalse(recipe.equals(failingRecipe) && failingRecipe.equals(recipe));
		assertFalse(recipe.hashCode() == failingRecipe.hashCode());
	}

}

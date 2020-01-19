package com.persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class CategoryUnitTest {

	@Test
	public void hashcodeAndEqualsCategoryTest() {
		Category category1 = new Category("Vegetarian");
		Category category2 = new Category("Vegetarian");
		
		assertTrue(category1.equals(category2) && category2.equals(category1));
		assertTrue(category1.hashCode() == category2.hashCode());
	}
	
	@Test
	public void failingEqualsAndHashcodeCategoryTest() {
		Category category = new Category("Meat");
		Category failingCategory = new Category("Vegetarian");
		
		assertFalse(category.equals(failingCategory) && failingCategory.equals(category));
		assertFalse(category.hashCode() == failingCategory.hashCode());
	}
}

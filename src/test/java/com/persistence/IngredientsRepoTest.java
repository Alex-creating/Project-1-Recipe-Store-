package com.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Ingredients;
import com.bae.persistence.repo.IngredientsRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class IngredientsRepoTest {
	
	@Autowired
	private IngredientsRepo ingRepo;
	
	private final Ingredients testIngredient = new Ingredients("Potatoes");

	private Ingredients testSavedIngredients;
	
	@Before
	public void init() {
		this.ingRepo.deleteAll();
		this.testSavedIngredients = this.ingRepo.save(this.testIngredient);
	}
	
	@Test
	public void testFindByName() {
		assertThat(this.ingRepo.findById(this.testIngredient.getIngredientId()).get()).isEqualTo(this.testSavedIngredients);
	}

}

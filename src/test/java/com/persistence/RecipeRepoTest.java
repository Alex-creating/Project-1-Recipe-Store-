package com.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepoTest {
	
	@Autowired
	private RecipeRepo recRepo;
	
	private final Recipe testRecipe = new Recipe("Lasagna", "Cook", 5, 120, 3);

	private Recipe testSavedRecipe;
	
	@Before
	public void init() {
		this.recRepo.deleteAll();
		this.testSavedRecipe = this.recRepo.save(this.testRecipe);
	}
	
	@Test
	public void testFindByName() {
		assertThat(this.recRepo.findById(this.testRecipe.getRecipeId())).isEqualTo(this.testSavedRecipe.getRecipeId());
	}
}

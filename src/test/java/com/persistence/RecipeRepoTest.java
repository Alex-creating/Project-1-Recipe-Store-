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
import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;

@SpringBootTest(classes = RecipeStoreApp.class)
@RunWith(SpringRunner.class)
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
		assertThat(this.recRepo.findById(this.testRecipe.getRecipeId()).get()).isEqualTo(this.testSavedRecipe);
	}
}

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
import com.bae.persistence.domain.Category;
import com.bae.persistence.repo.CategoryRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class CategoryRepoTest {
	
	@Autowired
	private CategoryRepo catRepo;
	
	private final Category testCategory = new Category("Meat");

	private Category testSavedCategory;
	
	@Before
	public void init() {
		this.catRepo.deleteAll();
		this.testSavedCategory = this.catRepo.save(this.testCategory);
	}
	
	@Test
	public void testFindByName() {
		assertThat(this.catRepo.findById(this.testCategory.getCategoryId()).get()).isEqualTo(this.testSavedCategory);
	}

}

package com.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Category;
import com.bae.persistence.repo.CategoryRepo;
import com.bae.service.CategoryService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
public class CategoryServiceIntegrationTest {
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private CategoryRepo catRepo;
	
	private Category testCategory;
	private Category testCategoryWithId;
	
	@Before
	public void init() {
		this.testCategory = new Category("Meat");
		this.testCategoryWithId = testCategory;
		
		this.catRepo.deleteAll();
		this.testCategoryWithId = this.catRepo.save(this.testCategory);
	}

	@Test
	public void testGetAllCategories() {
		assertThat(this.catService.getAllCategories()).isEqualTo(Arrays.asList(new Category[] { this.testCategoryWithId}));
	}
	
	@Test
	public void testCreateCategory() {
		assertEquals(this.testCategoryWithId, this.catService.createCategory(testCategory));
	}

//	@Test
	//public void testDeleteCategoryById() {
//		assertThat(this.catService.deleteCategoryById(this.testCategoryWithId.getCategoryId())).isFalse();
//	}

	@Test
	public void testFindCategoryByID() {
		assertThat(this.catService.findCategoryById(this.testCategoryWithId.getCategoryId())).isEqualTo(this.testCategoryWithId);
	}

	@Test
	public void testUpdateCategory() {
		Category newCategory = new Category("Vegetarian");
		Category updatedCategory = new Category(newCategory.getCategoryName());
		updatedCategory.setCategoryId(this.testCategoryWithId.getCategoryId());

		assertThat(this.catService.updateCategory(newCategory, this.testCategoryWithId.getCategoryId())).isEqualTo(updatedCategory);
	
}

}

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

import com.bae.persistence.domain.Category;
import com.bae.rest.CategoryController;
import com.bae.service.CategoryService;



@RunWith(SpringRunner.class)
public class CategoryControllerUnitTest {
	
	@InjectMocks
	private CategoryController controller;
	
	@Mock
	private CategoryService catService;
	
	private List<Category> catList;
	private Category testCat;
	private Category testCatWithId;
	final int id = 1;
	
	@Before
	public void init() {
		this.catList = new ArrayList<>();
		this.catList.add(testCat);
		this.testCat = new Category("Quick");
		this.testCatWithId = new Category(testCat.getCategoryName());
		this.testCatWithId.setCategoryId(id);
	}
	
	@Test
	public void createCategoryTest() {
		when(this.catService.createCategory(testCat)).thenReturn(testCatWithId);

		assertEquals(this.testCatWithId, this.controller.createCategory(testCat));

		verify(this.catService, times(1)).createCategory(this.testCat);
	}

	@Test
	public void deleteCategoryTest() {
		this.controller.deleteCategoryById(id);

		verify(this.catService, times(1)).deleteCategoryById(id);
	}

	@Test
	public void findCategoryByIdTest() {
		when(this.catService.findCategoryById(this.id)).thenReturn(this.testCatWithId);

		assertEquals(this.testCatWithId, this.controller.getCategory(this.id));

		verify(this.catService, times(1)).findCategoryById(this.id);
	}

	@Test
	public void getAllCategoryTest() {

		when(catService.getAllCategories()).thenReturn(this.catList);

		assertFalse("Controller has found no categories", this.controller.getAllCategories().isEmpty());

		verify(catService, times(1)).getAllCategories();
	}

	@Test
	public void updateCategoryTest() {
		
		Category newCategory = new Category("Dessert");
		Category updatedCategory = new Category(newCategory.getCategoryName());
		updatedCategory.setCategoryId(this.id);

		when(this.catService.updateCategory(newCategory, this.id)).thenReturn(updatedCategory);

		assertEquals(updatedCategory, this.controller.updateCategory(this.id, newCategory));

		verify(this.catService, times(1)).updateCategory(newCategory, this.id);
	}

}

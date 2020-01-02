package com.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.bae.exceptions.InvalidEntryException;
import com.bae.persistence.domain.Category;
import com.bae.persistence.repo.CategoryRepo;
import com.bae.service.CategoryService;

@RunWith(SpringRunner.class)
public class CategoryServiceUnitTest {
	
	@InjectMocks
	private CategoryService catService;
	
	@Mock
	private CategoryRepo catRepo;
	
	private List<Category> catList;
	
	private Category testCategory;
	private Category testCategoryWithId;
	private Category testFailingCategory;
	private Category testFailingCategoryWithId;
	
	final int id = 1;
	
	@Before
	public void init() {
		this.catList = new ArrayList<>();
		this.catList.add(testCategory);
		this.testCategory = new Category("Meat");
		this.testCategoryWithId = new Category(testCategory.getCategoryName());
		this.testCategoryWithId.setCategoryId(id);
		
		this.testFailingCategory = new Category("Food");
		this.testFailingCategoryWithId = new Category(testFailingCategory.getCategoryName());
		this.testFailingCategoryWithId.setCategoryId(id);
	}
	
	@Test
	public void createCategoryTest() {
		when(this.catRepo.save(testCategory)).thenReturn(testCategoryWithId);
		assertEquals(this.testCategoryWithId, this.catService.createCategory(testCategory));
		verify(this.catRepo, times(1)).save(this.testCategory);
	}
	
	@Test
	public void deleteCategoryTest() {
		when(this.catRepo.existsById(id)).thenReturn(true, false);

		this.catService.deleteCategoryById(id);

		verify(this.catRepo, times(1)).deleteById(id);

	}
	
	@Test
	public void findCategoryByIDTest() {
		when(this.catRepo.findById(this.id)).thenReturn(Optional.of(this.testCategoryWithId));

		assertEquals(this.testCategoryWithId, this.catService.findCategoryById(this.id));

		verify(this.catRepo, times(1)).findById(this.id);
	}

	@Test
	public void readCategoryTest() {

		when(catRepo.findAll()).thenReturn(this.catList);

		assertFalse("Controller has found no categories", this.catService.getAllCategories().isEmpty());

		verify(catRepo, times(1)).findAll();
	}

	@Test
	public void updateCategoryTest() {
		
		Category newCategory = new Category("Vegetarian");
		Category updatedCategory = new Category(newCategory.getCategoryName());
		updatedCategory.setCategoryId(this.id);

		when(this.catRepo.findById(this.id)).thenReturn(Optional.of(this.testCategoryWithId));
		when(this.catRepo.save(updatedCategory)).thenReturn(updatedCategory);

		assertEquals(updatedCategory, this.catService.updateCategory(newCategory, this.id));

		verify(this.catRepo, times(1)).findById(1);
		verify(this.catRepo, times(1)).save(updatedCategory);
	}
	
	@Test
	public void categoryNameTooShortTest() {
		this.testFailingCategory.setCategoryName("a");
		assertThrows(InvalidEntryException.class, () -> {
			this.catService.createCategory(this.testFailingCategory);	
		});
		this.testFailingCategory.setCategoryName("ab");
		assertThrows(InvalidEntryException.class, () -> {
			this.catService.createCategory(this.testFailingCategory);	
		});
		
	}
	
	@Test
	public void categoryNameTooLongTest() {
		this.testFailingCategory.setCategoryName("abcdefghijklmnopqrstabcdefghijklmnopqrstabcdefghijklim");
		assertThrows(InvalidEntryException.class, () -> {
			this.catService.createCategory(this.testFailingCategory);	
		});
	}
	
	@Test
	public void categoryContainsSpecialCharactersTest() {
		this.testFailingCategory.setCategoryName("Meat!!!!");
		assertThrows(InvalidEntryException.class, () -> {
			this.catService.createCategory(this.testFailingCategory);	
		});
		this.testFailingCategory.setCategoryName("Tasty?");
		assertThrows(InvalidEntryException.class, () -> {
			this.catService.createCategory(this.testFailingCategory);	
		});
		this.testFailingCategory.setCategoryName("Fast-To-Make");
		assertThrows(InvalidEntryException.class, () -> {
			this.catService.createCategory(this.testFailingCategory);	
		});
	}
	
	@Test
	public void categoryContainsOnlyNumbersTest() {
		this.testFailingCategory.setCategoryName("12345");
		assertThrows(InvalidEntryException.class, () -> {
			this.catService.createCategory(this.testFailingCategory);	
		});
	}
	
//	@Test
//	public void duplicateNameTest() {
//		testFailingCategory.setCategoryName("Meat");
//		this.catList.add(testFailingCategory);
//		when(this.catRepo.findAll()).thenReturn(this.catList);
//		assertTrue(this.catService.duplicateCategory(this.testCategory));
//		verify(this.catRepo, times(1)).findAll();
//	}
	
	@Test
	public void categoryNameAcceptableTest() {
		this.testCategory.setCategoryName("WordsAndNumbers123");
		when(this.catRepo.save(testCategory)).thenReturn(testCategoryWithId);
		assertEquals(this.testCategoryWithId, this.catService.createCategory(testCategory));
		
		this.testCategory.setCategoryName("Words with spaces");
		when(this.catRepo.save(testCategory)).thenReturn(testCategoryWithId);
		assertEquals(this.testCategoryWithId, this.catService.createCategory(testCategory));
		
		this.testCategory.setCategoryName("Fast and Delicious in 10 minutes");
		when(this.catRepo.save(testCategory)).thenReturn(testCategoryWithId);
		assertEquals(this.testCategoryWithId, this.catService.createCategory(testCategory));
		
		verify(this.catRepo, times(3)).save(this.testCategory);
		
	}
}

package com.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import com.bae.persistence.domain.Ingredients;
import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;
import com.bae.service.RecipeService;

@RunWith(SpringRunner.class)
public class RecipeServiceUnitTest {
	
	@InjectMocks
	private RecipeService recService;
	
	@Mock
	private RecipeRepo recRepo;
	
	@Mock
	private RecipeService recServiceMock;
	
	
	private Recipe testRecipe;
	private Recipe testRecipeWithId;
	private Recipe testRecipeWithId2;
	
	private Recipe testRecipeWithIngredients;
	private Recipe testRecipeWithCategories;
	private Recipe testFailingRecipe;
	
	private Ingredients testIng;
	private Ingredients testIngWithId;
	
	private Category testCat;
	private Category testCatWithId;
	
	private List<Recipe> recipeList;
	private List<Ingredients> ingList;
	private List<Category> catList;
	
	final int id = 1;
	final int id2 = 2;

	
	@Before
	public void init() {
		this.recipeList = new ArrayList<>();
		this.recipeList.add(testRecipe);
		this.testRecipe = new Recipe ("Lasagna", "Cook", 5, 120, 3);
		this.testRecipeWithId = new Recipe (testRecipe.getRecipeName(), testRecipe.getMethod(), testRecipe.getRating(), testRecipe.getTimeToMake(), testRecipe.getServingAmount());
		this.testRecipeWithId.setRecipeId(id);	
		this.testRecipeWithId2 = new Recipe (testRecipe.getRecipeName(), testRecipe.getMethod(), testRecipe.getRating(), testRecipe.getTimeToMake(), testRecipe.getServingAmount());
		this.testRecipeWithId2.setRecipeId(id2);
		
		this.testFailingRecipe = new Recipe("Pizza Cake", "Cook", 2, 3, 4);
		this.testRecipeWithIngredients = new Recipe (testRecipe.getRecipeName(), testRecipe.getMethod(), testRecipe.getRating(), testRecipe.getTimeToMake(), testRecipe.getServingAmount());
		this.testRecipeWithIngredients.setRecipeId(id2);
		this.testRecipeWithCategories = new Recipe (testRecipe.getRecipeName(), testRecipe.getMethod(), testRecipe.getRating(), testRecipe.getTimeToMake(), testRecipe.getServingAmount());
		this.testRecipeWithCategories.setRecipeId(id2);
		
		this.ingList = new ArrayList<>();
		this.ingList.add(testIng);
		this.testIng = new Ingredients ("Tomato");
		this.testIngWithId = new Ingredients (testIng.getIngredientName());
		this.testIngWithId.setIngredientId(id);
		
		this.catList = new ArrayList<>();
		this.catList.add(testCat);
		this.testCat = new Category ("Meat");
		this.testCatWithId = new Category (testCat.getCategoryName());
		this.testCatWithId.setCategoryId(id);

	}
	
	@Test
	public void createRecipeTest() {
		when(this.recRepo.save(testRecipe)).thenReturn(testRecipeWithId);
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));
		verify(this.recRepo, times(1)).save(this.testRecipe);
	}
	
	@Test
	public void deleteRecipeTest() {
		when(this.recRepo.existsById(1)).thenReturn(true, false);
		this.recService.deleteRecipeById(1);
		
		verify(this.recRepo, times(1)).deleteById(1);
//		verify(this.recRepo, times(2)).findById(id);
	}
	
	@Test
	public void findRecipeByIdTest() {
		when(this.recRepo.findById(this.id)).thenReturn(Optional.of(this.testRecipeWithId));
		assertEquals(this.testRecipeWithId, this.recService.findRecipeById(this.id));
		verify(this.recRepo, times(1)).findById(this.id);
	}
	
	@Test
	public void findAllRecipesTest() {
		when(recRepo.findAll()).thenReturn(this.recipeList);
		assertFalse("There are no recipes", this.recService.getAllRecipes().isEmpty());
		verify(this.recRepo,times(1)).findAll();
	}
	
	@Test
	public void updateRecipeTest() { 
		Recipe newRecipe = new Recipe("Pizza", "Boil",2, 20,5);
		Recipe updatedRecipe = new Recipe (newRecipe.getRecipeName(), newRecipe.getMethod(), newRecipe.getRating(), newRecipe.getTimeToMake(), newRecipe.getServingAmount());
		updatedRecipe.setRecipeId(this.id);
		
		when(this.recRepo.findById(this.id)).thenReturn(Optional.of(this.testRecipeWithId));
		when(this.recRepo.save(updatedRecipe)).thenReturn(updatedRecipe);
		
		assertEquals(updatedRecipe, this.recService.updateRecipe(newRecipe, this.id));
		verify(this.recRepo, times(1)).findById(1);
		verify(this.recRepo, times(1)).save(updatedRecipe);
	}
	
	@Test
	public void recipeNameTooShortTest() {
		
		this.testFailingRecipe.setRecipeName("a");
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);	
		});
		this.testFailingRecipe.setRecipeName("ab");
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);	
		});
	}
	
	@Test 
	public void addAndRemoveIngredients() {
		when(this.recRepo.findById(this.id2)).thenReturn(Optional.of(this.testRecipeWithIngredients));
		recServiceMock.addIngredientToRecipe(testRecipeWithIngredients.getRecipeId(), ingList);
		recServiceMock.removePreviousIngredients(testRecipeWithIngredients.getRecipeId());
		
		this.ingList.add(testIng);
		
		assertEquals(testRecipeWithIngredients, testRecipeWithId2);
		verify(this.recServiceMock, times(1)).addIngredientToRecipe(testRecipeWithIngredients.getRecipeId(), this.ingList);
		verify(this.recServiceMock, times(1)).removePreviousIngredients(testRecipeWithIngredients.getRecipeId());
	}
	
	@Test 
	public void addAndRemoveCategories() {
		when(this.recRepo.findById(this.id2)).thenReturn(Optional.of(this.testRecipeWithCategories));
		recServiceMock.addCategoriesToRecipe(testRecipeWithCategories.getRecipeId(), catList);
		recServiceMock.removePreviousCategories(testRecipeWithCategories.getRecipeId());
		
		this.catList.add(testCat);
		
		assertEquals(testRecipeWithCategories, testRecipeWithId2);
		verify(this.recServiceMock, times(1)).addCategoriesToRecipe(testRecipeWithCategories.getRecipeId(), this.catList);
		verify(this.recServiceMock, times(1)).removePreviousCategories(testRecipeWithCategories.getRecipeId());
	}
		

	
	@Test
	public void recipeNameTooLongTest() {
		
		
		this.testFailingRecipe.setRecipeName("abcdefghijklmnopqrstabcdefghijklmnopqrstabcdefghijklim");
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
	}
	
	@Test
	public void recipeNameContainsSpecialCharactersTest() {
		this.testFailingRecipe.setRecipeName("Lasagna!!");
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
		this.testFailingRecipe.setRecipeName("Bird Pie!!");
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
	}
	
	@Test
	public void recipeNameContainsOnlyNumbers() {
		this.testFailingRecipe.setRecipeName("123123");
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
	}
	@Test
	public void acceptableRecipeNameTest() {
		this.testRecipe.setRecipeName("Tasty Pizza");
		when(this.recRepo.save(testRecipe)).thenReturn(testRecipeWithId);
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));
		
		this.testRecipe.setRecipeName("1 Salad");
		when(this.recRepo.save(testRecipe)).thenReturn(testRecipeWithId);
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));
	}
	
	
	@Test 
	public void recipeRatingTest() {
		this.testFailingRecipe.setRating(0);
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
		
		this.testFailingRecipe.setRating(12);
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
		
		this.testRecipe.setRating(3);
		when(this.recRepo.save(testRecipe)).thenReturn(testRecipeWithId);
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));
		
	}
	
	@Test
	public void recipeServingAmountTest() {
		this.testFailingRecipe.setServingAmount(0);
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
		
		this.testRecipe.setServingAmount(12);
		when(this.recRepo.save(testRecipe)).thenReturn(testRecipeWithId);
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));
	}
	
	@Test
	public void recipeTimeToMakeTest() {
		this.testFailingRecipe.setTimeToMake(0);
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
		
		this.testFailingRecipe.setTimeToMake(601);
		assertThrows(InvalidEntryException.class, () -> {
			this.recService.createRecipe(this.testFailingRecipe);
		});
		
		this.testRecipe.setTimeToMake(120);
		when(this.recRepo.save(testRecipe)).thenReturn(testRecipeWithId);
		assertEquals(this.testRecipeWithId, this.recService.createRecipe(testRecipe));

	}
	

}

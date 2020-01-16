package com.rest;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bae.RecipeStoreApp;
import com.bae.persistence.domain.Category;
import com.bae.persistence.domain.Ingredients;
import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.CategoryRepo;
import com.bae.persistence.repo.IngredientsRepo;
import com.bae.persistence.repo.RecipeRepo;
import com.bae.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
@AutoConfigureMockMvc
public class RecipeControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private RecipeRepo recRepo;
	
	@Autowired
	private IngredientsRepo ingRepo;
	
	@Autowired
	private CategoryRepo catRepo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private int id;

	
	private Recipe testRec;
	
	private Recipe testRecWithId;
	
	@Autowired
	private RecipeService recService;
	
	private Ingredients ingredient;
	private Ingredients ingredientWithId;
	private Category testCategory;
	private Category testCategoryWithId;
	
	@Before 
	public void init() {
		this.recRepo.deleteAll();
		this.ingRepo.deleteAll();
		this.catRepo.deleteAll();
		
		this.testRec = new Recipe("Pizza", "cook", 5, 5, 5);
		this.testRecWithId = this.recRepo.save(this.testRec);
		this.id = this.testRecWithId.getRecipeId();	
		
		this.testCategory = new Category("Fast");
		this.testCategoryWithId = this.catRepo.save(this.testCategory);

		
		this.ingredient = new Ingredients("Potato");
		this.ingredientWithId = this.ingRepo.save(ingredient);
		
	}
	
	@After
	public void after() {
		this.recRepo.deleteAll();
		this.ingRepo.deleteAll();
		this.catRepo.deleteAll();
	}
	
	@Test
	public void testCreateRecipe() throws Exception {
		String result = this.mock.perform(request(HttpMethod.POST, "/createRecipe").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(testRec)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testRecWithId), result);
	}
	
	@Test
	public void testDeleteRecipe() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/deleteRecipe/" + this.id)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllRecipe() throws Exception {
		List<Recipe> recList = new ArrayList<>();
		recList.add(this.testRecWithId);

		String content = this.mock.perform(request(HttpMethod.GET, "/getAllRec").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("1" +recList); 
		System.out.println("2" +content);
		assertEquals(this.mapper.writeValueAsString(recList), content);
	}

	@Test
	public void testUpdateRecipe() throws Exception {
		Recipe newRec = new Recipe("Lasagna", "bake", 3, 3, 125);
		Recipe updatedRecipe = new Recipe(newRec.getRecipeName(), newRec.getMethod(), newRec.getRating(), newRec.getTimeToMake(), newRec.getServingAmount());
		updatedRecipe.setRecipeId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/updateRecipe/?recipeId=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newRec)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.mapper.writeValueAsString(updatedRecipe), result);
	}
	@Test
	public void testPatchIngredients() throws Exception {
		Recipe patchedRecipe = new Recipe("Pizza", "cook", 5, 5, 5);
		Recipe testRecWithIngredients = new Recipe(patchedRecipe.getRecipeName(), patchedRecipe.getMethod(), patchedRecipe.getRating(), patchedRecipe.getTimeToMake(), patchedRecipe.getServingAmount());
		testRecWithIngredients.setRecipeId(this.id);
		
		Set<Ingredients> ingredientToAdd = new HashSet<>();
		ingredientToAdd.add(ingredientWithId);
		
		testRecWithIngredients.getIngredients().addAll(ingredientToAdd);
		
		String result = this.mock
				.perform(request(HttpMethod.PATCH, "/attachIngredient/" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(ingredientToAdd)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.mapper.writeValueAsString(testRecWithIngredients), result);
	}
	
	@Test
	public void testPatchCategories() throws Exception {
		Recipe patchedRecipe = new Recipe("Pizza", "cook", 5, 5, 5);
		Recipe testRecWithCategories = new Recipe(patchedRecipe.getRecipeName(), patchedRecipe.getMethod(), patchedRecipe.getRating(), patchedRecipe.getTimeToMake(), patchedRecipe.getServingAmount());
		testRecWithCategories.setRecipeId(this.id);
		
		Set<Category> categoryToAdd = new HashSet<>();
		categoryToAdd.add(testCategoryWithId);
		
		testRecWithCategories.getCategories().addAll(categoryToAdd);
		
		String result = this.mock
				.perform(request(HttpMethod.PATCH, "/attachCategory/" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(categoryToAdd)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.mapper.writeValueAsString(testRecWithCategories), result);
	}
	
}

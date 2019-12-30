package com.rest;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
@AutoConfigureMockMvc
public class RecipeControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private RecipeRepo recRepo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private int id;
	
	private Recipe testRec;
	
	private Recipe testRecWithId;
	
	@Before 
	public void init() {
		this.recRepo.deleteAll();
		
		this.testRec = new Recipe("Pizza", "cook", 5, 5, 5);
		this.testRecWithId = this.recRepo.save(this.testRec);
		this.id = this.testRecWithId.getRecipeId();	
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

		assertEquals(this.mapper.writeValueAsString(recList), content);
	}

	@Test
	public void testUpdateRecipe() throws Exception {
		Recipe newRec = new Recipe("Lasagna", "bake", 12, 12, 125);
		Recipe updatedRecipe = new Recipe(newRec.getRecipeName(), newRec.getMethod(), newRec.getRating(), newRec.getTimeToMake(), newRec.getServingAmount());
		updatedRecipe.setRecipeId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/updateRecipe/?recipeId=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newRec)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.mapper.writeValueAsString(updatedRecipe), result);
	}
	
}

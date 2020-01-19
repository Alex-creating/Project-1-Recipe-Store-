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
import com.bae.persistence.domain.Ingredients;
import com.bae.persistence.repo.IngredientsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
@AutoConfigureMockMvc
public class IngredientControllerIntegrationTest {
		
		@Autowired
		private MockMvc mock;
		
		@Autowired
		private IngredientsRepo ingRepo;
		
		private ObjectMapper mapper = new ObjectMapper();
		
		private int id;
		
		private Ingredients testIng;
		
		private Ingredients testIngWithId;
		
		@Before 
		public void init() {
			this.ingRepo.deleteAll();
			
			this.testIng = new Ingredients("Flour");
			this.testIngWithId = this.ingRepo.save(this.testIng);
			this.id = this.testIngWithId.getIngredientId();	
		}
		
		@Test
		public void testCreateIngredient() throws Exception {
			String result = this.mock.perform(request(HttpMethod.POST, "/createIngredient").contentType(MediaType.APPLICATION_JSON)
					.content(this.mapper.writeValueAsString(testIng)).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			assertEquals(this.mapper.writeValueAsString(testIngWithId), result);
		}
		
		@Test
		public void testDeleteIngredient() throws Exception {
			this.mock.perform(request(HttpMethod.DELETE, "/deleteIngredient/" + this.id)).andExpect(status().isOk());
		}

		@Test
		public void testGetAllIngredients() throws Exception {
			List<Ingredients> ingList = new ArrayList<>();
			ingList.add(this.testIngWithId);

			String content = this.mock.perform(request(HttpMethod.GET, "/getAllIng").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			assertEquals(this.mapper.writeValueAsString(ingList), content);
		}

		@Test
		public void testUpdateCategory() throws Exception {
			Ingredients newIng = new Ingredients("Tomatoes");
			Ingredients updatedIng = new Ingredients(newIng.getIngredientName());
			updatedIng.setIngredientId(this.id);

			String result = this.mock
					.perform(request(HttpMethod.PUT, "/updateIngredient/?ingredientId=" + this.id).accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newIng)))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
			assertEquals(this.mapper.writeValueAsString(updatedIng), result);
		}


}

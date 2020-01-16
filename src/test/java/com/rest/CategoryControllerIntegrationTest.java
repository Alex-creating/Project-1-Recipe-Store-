package com.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.bae.persistence.repo.CategoryRepo;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeStoreApp.class)
@AutoConfigureMockMvc
public class CategoryControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private CategoryRepo catRepo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private int id;
	
	private Category testCat;
	
	private Category testCatWithId;
	
	@Before 
	public void init() {
		this.catRepo.deleteAll();
		
		this.testCat = new Category("Meat");
		this.testCatWithId = this.catRepo.save(this.testCat);
		this.id = this.testCatWithId.getCategoryId();	
	}
	
	@After
	public void after() {
		this.catRepo.deleteAll();
	}

	
	
	@Test
	public void testCreateCategory() throws Exception {
		String result = this.mock.perform(request(HttpMethod.POST, "/createCategory").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(testCat)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testCatWithId), result);
	}
	
	@Test
	public void testDeleteCategory() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/deleteCategory/" + this.id)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllCategory() throws Exception {
		List<Category> catList = new ArrayList<>();
		catList.add(this.testCatWithId);

		String content = this.mock.perform(request(HttpMethod.GET, "/getAllCat").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(catList), content);
	}

	@Test
	public void testUpdateCategory() throws Exception {
		Category newCat = new Category("Vegetarian");
		Category updatedCat = new Category(newCat.getCategoryName());
		updatedCat.setCategoryId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/updateCategory/?categoryId=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newCat)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.mapper.writeValueAsString(updatedCat), result);
	}

}

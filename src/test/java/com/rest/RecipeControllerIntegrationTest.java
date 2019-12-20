package com.rest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bae.persistence.domain.Recipe;
import com.bae.persistence.repo.RecipeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
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
	

}

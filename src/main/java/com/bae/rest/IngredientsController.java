package com.bae.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.persistence.domain.Ingredients;
import com.bae.service.IngredientsService;


@RestController
public class IngredientsController {
	
	private IngredientsService ingService;
	
	@Autowired
	public IngredientsController (IngredientsService ingService) {
		super();
		 this.ingService = ingService;
	}
	
	@PostMapping("/createIngredient")
	public Ingredients createIngredients(@RequestBody Ingredients ingredient) {
	return this.ingService.createIngredient(ingredient);
	}
	
	@DeleteMapping("/deleteIngredient/{id}")
	public void deleteIngredientById(@PathVariable int id) {
		this.ingService.deleteIngredientById(id);
	} 
	
	@GetMapping("/getIng/{id}")
	public Ingredients getIngredient(@PathVariable int id) {
		return this.ingService.findIngredientById(id);
}
	@GetMapping("/getAllIng")
	public List<Ingredients> getAllIngredients(){
		return this.ingService.getAllIngredients();
	}
	
	@PutMapping("/updateIngredient")
	public Ingredients updateIngredient(@PathParam("id") int ingredientId, @RequestBody Ingredients ingredientToUpdate) {
		return this.ingService.updateIngredients(ingredientToUpdate, ingredientId);
	}

}

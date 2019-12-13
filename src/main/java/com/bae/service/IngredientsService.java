package com.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.exceptions.IngredientNotFound;

import com.bae.persistence.domain.Ingredients;

import com.bae.persistence.repo.IngredientsRepo;

@Service
public class IngredientsService {

	private IngredientsRepo ingRepo;

	public IngredientsService(IngredientsRepo ingRepo) {
		this.ingRepo = ingRepo;
	}

	public List<Ingredients> getAllIngredients() {
		return ingRepo.findAll();
	}

	public Ingredients findIngredientById(int id) {
		return this.ingRepo.findById(id).orElseThrow(() -> new IngredientNotFound());
	}

	public void deleteIngredientByID(int ingredientId) {
		ingRepo.deleteById(ingredientId);
	}

	public Ingredients createIngredient(Ingredients ingredientToAdd) {
		return ingRepo.save(ingredientToAdd);
	}

	public Ingredients updateIngredients(Ingredients ingredient, int id) {
		Ingredients ingredientToUpdate = findIngredientById(id);
		ingredientToUpdate.setIngredientName(ingredient.getIngredientName());

		return this.ingRepo.save(ingredientToUpdate);
	}

}

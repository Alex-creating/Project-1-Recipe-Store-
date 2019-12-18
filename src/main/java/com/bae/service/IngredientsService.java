package com.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.exceptions.IngredientNotFound;
import com.bae.exceptions.InvalidEntryException;
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
		return this.ingRepo.findById(id).orElseThrow(IngredientNotFound::new);
	}

	public void deleteIngredientById(int ingredientId) {
		ingRepo.deleteById(ingredientId);
	}

	public Ingredients createIngredient(Ingredients ingredientToAdd) {
		if (ingredientToAdd.getIngredientName().length()<2 || ingredientToAdd.getIngredientName().length()>50) {
			 throw new InvalidEntryException();
			}
		return ingRepo.save(ingredientToAdd);
			
	}

	public Ingredients updateIngredients(Ingredients ingredient, int id) {
		Ingredients ingredientToUpdate = findIngredientById(id);
		ingredientToUpdate.setIngredientName(ingredient.getIngredientName());

		return this.ingRepo.save(ingredientToUpdate);
	}

}

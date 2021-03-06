package com.bae.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bae.exceptions.IngredientNotFound;
import com.bae.exceptions.InvalidEntryException;
import com.bae.persistence.domain.Ingredients;

import com.bae.persistence.repo.IngredientsRepo;

@Service
public class IngredientsService {

	private IngredientsRepo ingRepo;
	
	String regex = "^[a-zA-Z0-9 ]+$";
	Pattern pattern = Pattern.compile(regex);
	
	public boolean specialCharacterChecker(String checkedName) {
		Matcher matcher = pattern.matcher(checkedName);
		return matcher.matches();
	}
	

	public IngredientsService(IngredientsRepo ingRepo) {
		this.ingRepo = ingRepo;
	}

	public List<Ingredients> getAllIngredients() {
		return ingRepo.findAll();
	}

	public Ingredients findIngredientById(int id) {
		return this.ingRepo.findById(id).orElseThrow(IngredientNotFound::new);
	}

	public boolean deleteIngredientById(int ingredientId) {
		ingRepo.deleteById(ingredientId);
		return this.ingRepo.existsById(ingredientId);
	}

	public Ingredients createIngredient(Ingredients ingredientToAdd) {
		if (ingredientToAdd.getIngredientName().length()<3 || ingredientToAdd.getIngredientName().length()>50) {
			 throw new InvalidEntryException();
			}
		if (!specialCharacterChecker(ingredientToAdd.getIngredientName())) {
			throw new InvalidEntryException();
		}
		if (StringUtils.isNumeric(ingredientToAdd.getIngredientName())) {
			throw new InvalidEntryException();
		}
		
		return ingRepo.save(ingredientToAdd);
			
	}

	public Ingredients updateIngredients(Ingredients ingredient, int id) {
		Ingredients ingredientToUpdate = findIngredientById(id);
		ingredientToUpdate.setIngredientName(ingredient.getIngredientName());
		
		if (ingredientToUpdate.getIngredientName().length()<3 || ingredientToUpdate.getIngredientName().length()>50) {
			 throw new InvalidEntryException();
			}
		if (!specialCharacterChecker(ingredientToUpdate.getIngredientName())) {
			throw new InvalidEntryException();
		}
		if (StringUtils.isNumeric(ingredient.getIngredientName())) {
			throw new InvalidEntryException();
		}


		return this.ingRepo.save(ingredientToUpdate);
	}

}

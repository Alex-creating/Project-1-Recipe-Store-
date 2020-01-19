package com.bae.persistence.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Ingredients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ingredientId;
	private String ingredientName;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "recipeHasIngredients")
	Set<Recipe> ingredientsAreInRecipes;

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ingredientId;
		result = prime * result + ((ingredientName == null) ? 0 : ingredientName.hashCode());
		return result;
	}


	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredients other = (Ingredients) obj;
		if (ingredientId != other.ingredientId)
			return false;
		if (ingredientName == null) {
			if (other.ingredientName != null)
				return false;
		} 
		else if (!ingredientName.equals(other.ingredientName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ingredient ID=" + ingredientId + ", Ingredient Name=" + ingredientName;
	}

	public Ingredients() {
	}

	public Ingredients(String ingredientName) {
		super();
		this.ingredientName = ingredientName;
	}

}

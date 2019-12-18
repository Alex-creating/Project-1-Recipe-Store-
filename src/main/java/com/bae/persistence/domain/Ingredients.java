package com.bae.persistence.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Ingredients {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int ingredientId;
	private String ingredientName;
	
	@ManyToMany
	@JoinTable(
	name = "recipe_ingredient ",
	joinColumns = @JoinColumn (name = "recipeId"),
	inverseJoinColumns = @JoinColumn (name = "ingredientId"))
	Set<Ingredients> recipeHasIngredients;

	
	
	
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
		result = prime * result + ((recipeHasIngredients == null) ? 0 : recipeHasIngredients.hashCode());
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
		} else if (!ingredientName.equals(other.ingredientName))
			return false;
		if (recipeHasIngredients == null) {
			if (other.recipeHasIngredients != null)
				return false;
		} else if (!recipeHasIngredients.equals(other.recipeHasIngredients))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ingredient ID=" + ingredientId + ", Ingredient Name=" + ingredientName;
	}
	
	public Ingredients() {}
	
	
	public Ingredients(String ingredientName) {
		super();

		this.ingredientName = ingredientName;
	}
	
	
	
	

}

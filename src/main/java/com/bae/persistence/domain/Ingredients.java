package com.bae.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredients {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int ingredientId;
	private String ingredientName;
	
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
		return true;
	}
	@Override
	public String toString() {
		return "Ingredient ID=" + ingredientId + ", Ingredient Name=" + ingredientName;
	}
	
	public Ingredients() {}
	
	
	public Ingredients(int ingredientId, String ingredientName) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
	}
	
	
	
	

}

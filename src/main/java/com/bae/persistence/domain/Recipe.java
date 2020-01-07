package com.bae.persistence.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recipeId;
	private String recipeName;
	private String method;
	private int rating;
	private int timeToMake;
	private int servingAmount;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "categoryId"), inverseJoinColumns = @JoinColumn(name = "recipeId"))
	private Set<Category> recipeHasCategories = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "ingredientId"), inverseJoinColumns = @JoinColumn(name = "recipeId"))
	private Set<Ingredients> recipeHasIngredients = new HashSet<>();

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getTimeToMake() {
		return timeToMake;
	}

	public void setTimeToMake(int timeToMake) {
		this.timeToMake = timeToMake;
	}

	public int getServingAmount() {
		return servingAmount;
	}

	public void setServingAmount(int servingAmount) {
		this.servingAmount = servingAmount;
	}

	public Set<Ingredients> getIngredients() {
		return recipeHasIngredients;
	}

	public Set<Category> getCategories() {
		return recipeHasCategories;
	}

	public void setIngredients(Set<Ingredients> ingredients) {
		this.recipeHasIngredients = ingredients;
	}

	public void setCategories(Set<Category> category) {
		this.recipeHasCategories = category;
	}

	public Recipe() {
	}

	public Recipe(String recipeName, String method, int rating, int timeToMake, int servingAmount) {
		super();

		this.recipeName = recipeName;
		this.method = method;
		this.rating = rating;
		this.timeToMake = timeToMake;
		this.servingAmount = servingAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + rating;
		result = prime * result + recipeId;
		result = prime * result + ((recipeName == null) ? 0 : recipeName.hashCode());
		result = prime * result + servingAmount;
		result = prime * result + timeToMake;
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
		Recipe other = (Recipe) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (rating != other.rating)
			return false;
		if (recipeId != other.recipeId)
			return false;
		if (recipeName == null) {
			if (other.recipeName != null)
				return false;
		} else if (!recipeName.equals(other.recipeName))
			return false;
		if (servingAmount != other.servingAmount)
			return false;
		if (timeToMake != other.timeToMake)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recipe ID= " + recipeId + ", Recipe Name= " + recipeName + ", Method= " + method + ", Rating= " + rating
				+ ", Time To Make= " + timeToMake + " minutes" + ", Serving Amount= " + servingAmount;
	}

}

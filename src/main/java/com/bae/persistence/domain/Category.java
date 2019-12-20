package com.bae.persistence.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String categoryName;
	
	@ManyToMany
	@JoinTable(
	name = "recipe_category ",
	joinColumns = @JoinColumn (name = "recipeId"),
	inverseJoinColumns = @JoinColumn (name = "categoryId"))
	Set<Category> recipeHasCategories;
	

	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((recipeHasCategories == null) ? 0 : recipeHasCategories.hashCode());
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
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (recipeHasCategories == null) {
			if (other.recipeHasCategories != null)
				return false;
		} else if (!recipeHasCategories.equals(other.recipeHasCategories))
			return false;
		return true;
	}
	
	public Category() {}
	
	public Category(String categoryName) {
		super();

		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		return "Category ID= " + categoryId + ", Category Name=" + categoryName;
	}
	
	
	
	

}

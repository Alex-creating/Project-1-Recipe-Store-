package com.bae.persistence.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String categoryName;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="recipeHasCategories")
	Set<Recipe> recipeHasCategories;
	

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
		} 
		else if (!categoryName.equals(other.categoryName))
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

package com.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.exceptions.CategoryNotFound;
import com.bae.exceptions.InvalidEntryException;
import com.bae.persistence.domain.Category;
import com.bae.persistence.repo.CategoryRepo;


@Service
public class CategoryService {

	private CategoryRepo catRepo;

	public CategoryService(CategoryRepo catRepo) {
		this.catRepo = catRepo;
	}
	
	
	public List<Category> getAllCategories()
	{
		return catRepo.findAll();
	}
	
	public 	Category findCategoryById(int id) {
		return this.catRepo.findById(id).orElseThrow(CategoryNotFound::new);
	}
	

	public void deleteCategoryById(int categoryId) 
	{
		catRepo.deleteById(categoryId);
	}
	
	public Category createCategory(Category categoryToAdd) 
	{
		
		if (categoryToAdd.getCategoryName().length()<2 || categoryToAdd.getCategoryName().length()>50) {
			throw new InvalidEntryException();
		}	
		return catRepo.save(categoryToAdd);
		
	}
	
	public Category updateCategory(Category category, int id) {
		Category categoryToUpdate = findCategoryById(id);
	
		categoryToUpdate.setCategoryName(category.getCategoryName());

		return this.catRepo.save(categoryToUpdate);
	}
}

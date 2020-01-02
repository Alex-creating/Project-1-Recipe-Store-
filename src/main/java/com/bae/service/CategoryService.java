package com.bae.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.bae.exceptions.CategoryNotFound;
import com.bae.exceptions.InvalidEntryException;
import com.bae.persistence.domain.Category;
import com.bae.persistence.repo.CategoryRepo;


@Service
public class CategoryService {

	private CategoryRepo catRepo;
 
	String regex = "^[a-zA-Z0-9 ]+$";
	Pattern pattern = Pattern.compile(regex);
	
	public boolean specialCharacterChecker(String checkedName) {
		Matcher matcher = pattern.matcher(checkedName);
		return matcher.matches();
	}
	
	public Boolean duplicateCategory(Category category) {
		return this.getAllCategories().contains(category);
	}
	

	public CategoryService(CategoryRepo catRepo) {
		this.catRepo = catRepo;
	}
	
	
	public List<Category> getAllCategories()
	{
		return catRepo.findAll();
	}
	
	public Category findCategoryById(int id) {
		return this.catRepo.findById(id).orElseThrow(CategoryNotFound::new);
	}
	

	public void deleteCategoryById(int categoryId) 
	{
		catRepo.deleteById(categoryId);
	}
	
	public Category createCategory(Category categoryToAdd) 
	{
		
		if (categoryToAdd.getCategoryName().length()<3 || categoryToAdd.getCategoryName().length()>50) {
			throw new InvalidEntryException();
		}	
		if (specialCharacterChecker(categoryToAdd.getCategoryName()) == false) {
			throw new InvalidEntryException();
		}
		if (duplicateCategory(categoryToAdd)) {
			throw new InvalidEntryException();
		}
		
		return catRepo.save(categoryToAdd);
		
	}
	
	public Category updateCategory(Category category, int id) {
		Category categoryToUpdate = findCategoryById(id);
	
		categoryToUpdate.setCategoryName(category.getCategoryName());
		
		if (categoryToUpdate.getCategoryName().length()<3 || categoryToUpdate.getCategoryName().length()>50) {
			throw new InvalidEntryException();
			}
		if (specialCharacterChecker(categoryToUpdate.getCategoryName()) == false) {
			throw new InvalidEntryException();
		}
		if (duplicateCategory(categoryToUpdate)) {
			throw new InvalidEntryException();
		}
		
		return this.catRepo.save(categoryToUpdate);
	}
}

package com.bae.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
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
	

	public boolean deleteCategoryById(int categoryId) 
	{
		catRepo.deleteById(categoryId);
		return this.catRepo.existsById(categoryId);
	}
	
	public Category createCategory(Category categoryToAdd) 
	{
		
		if (categoryToAdd.getCategoryName().length()<3 || categoryToAdd.getCategoryName().length()>50) {
			throw new InvalidEntryException();
		}	
		if (!specialCharacterChecker(categoryToAdd.getCategoryName())) {
			throw new InvalidEntryException();
		}
		if (StringUtils.isNumeric(categoryToAdd.getCategoryName())) {
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
		if (!specialCharacterChecker(categoryToUpdate.getCategoryName())) {
			throw new InvalidEntryException();
		}
		if (StringUtils.isNumeric(categoryToUpdate.getCategoryName())) {
			throw new InvalidEntryException();
		}
		
		return this.catRepo.save(categoryToUpdate);
	}
}

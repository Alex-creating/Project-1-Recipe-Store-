package com.bae.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.persistence.domain.Category;
import com.bae.service.CategoryService;


@RestController
public class CategoryController {
	
	private CategoryService catService;
	
	@Autowired
	public CategoryController (CategoryService catService) {
		super();
		 this.catService = catService;
	}
	
	@PostMapping("/createCategory")
	public Category createCategory(@RequestBody Category category) {
	return this.catService.createCategory(category);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public void deleteCategoryById(@PathVariable int id) {
		this.catService.deleteCategoryByID(id);
	} 
	
	@GetMapping("/getCat/{id}")
	public Category getCategory(@PathVariable int id) {
		return this.catService.findCategoryById(id);
}
	@GetMapping("getAllCat")
	public List<Category> getAllCategories(){
		return this.catService.getAllCategories();
	}
	
	@PutMapping("updateCategory")
	public Category updateCategory(@PathParam("id") int categoryId, @RequestBody Category categoryToUpdate) {
		return this.catService.updateCategory(categoryToUpdate, categoryId);
	}

}

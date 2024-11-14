package com.app.blog.service;

import java.util.List;

import com.app.blog.entity.CategoryEntity;
import com.app.blog.request.Category;

public interface CategoryService {

	
	 Integer createCategory(Category category);
	
	 Integer updateCategory(Category category,Integer id);
	 
	 public void deleteCategory(Integer id);
	 
	 public List<Category> getAllCategories();
	
}

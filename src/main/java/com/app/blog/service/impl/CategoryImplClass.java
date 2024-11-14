package com.app.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entity.CategoryEntity;
import com.app.blog.repository.CategoryRepository;
import com.app.blog.request.Category;
import com.app.blog.service.CategoryService;

@Service
public class CategoryImplClass implements CategoryService{
	
	@Autowired
	CategoryRepository repository;

	@Override
	public Integer createCategory(Category category) {
        CategoryEntity categoryEntity=new CategoryEntity();
        BeanUtils.copyProperties(category, categoryEntity);
        CategoryEntity entity=repository.save(categoryEntity);
		return entity.getId();
	}

	@Override
	public Integer updateCategory(Category category, Integer id) {
      Optional<CategoryEntity> optional=repository.findById(id);
      if(optional.isPresent()) {
      CategoryEntity entity= optional.get();
      BeanUtils.copyProperties(category, entity);
      repository.save(entity);
      }
		return optional.get().getId();
	}

	@Override
	public void deleteCategory(Integer id) {
		Optional<CategoryEntity> optional=repository.findById(id);
	      if(optional.isPresent()) {
	      repository.deleteById(id);
	      }
	}

	@Override
	public List<Category> getAllCategories() {
		List<CategoryEntity> list=repository.findAll();
		List<Category> categories=new ArrayList<>();
		for(CategoryEntity c:list) {
			Category cat=new Category();
			cat.setCategoryName(c.getCategoryName());
			cat.setId(c.getId());
			categories.add(cat);
		}
		return categories;
	}

}

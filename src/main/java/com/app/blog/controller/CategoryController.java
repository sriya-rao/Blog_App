package com.app.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.entity.CategoryEntity;
import com.app.blog.request.Category;
import com.app.blog.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> createCategory(@RequestBody Category category){
		Integer id=service.createCategory(category);
		return new ResponseEntity<String>("Created Success with id:"+id, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Category>> getAllEntity(){
		List<Category> list=service.getAllCategories();
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable  Integer id){
		service.deleteCategory(id);
		return new ResponseEntity<String>("Deleted Successfully id:"+id, HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable Integer id,@RequestBody Category category){
		Integer idInteger=service.updateCategory(category, id);
		return new ResponseEntity<String>("Updated successfully with id:"+idInteger, HttpStatus.OK);
	}
}

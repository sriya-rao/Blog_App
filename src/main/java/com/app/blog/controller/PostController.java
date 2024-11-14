package com.app.blog.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.configurations.AppConstants;
import com.app.blog.entity.PostEntity;
import com.app.blog.request.Category;
import com.app.blog.request.Post;
import com.app.blog.response.PostResponse;
import com.app.blog.service.PostService;

@RestController
@RequestMapping("/api/post")
@CrossOrigin
public class PostController {
	
	@Autowired
	PostService service;

	@PostMapping("/create/category/{catId}/user/{userId}")
	public ResponseEntity<String> createPost(@RequestBody Post post,
			@PathVariable Integer catId,
			@PathVariable Integer userId){
		 
		Integer id=service.createPost(post, catId, userId);
		
		return new ResponseEntity<String>("Created Success with id:"+id, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<String> updatepost(@RequestBody Post post) {
		Integer id=service.updatepost(post);
		return new ResponseEntity<String>("Updated Success with id:"+id, HttpStatus.OK);		
	}
	
	
	
	@GetMapping("/all")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy){
		
		PostResponse  postResponse=service.getAllPosts(pageNumber,pageSize,sortBy);
		
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/category/{catId}")
	public List<Post> getPostsByCategory(@PathVariable   Integer catId) {
		List<Post> list=service.getPostsByCategory(catId);
		return list;
		
	}

	
	@GetMapping("/user/{userId}")
	public List<Post> getPostsByUser(@PathVariable  Integer userId) {
		List<Post> list=service.getPostsByUser(userId);
		return list;
		
	}
	
	@GetMapping("/single/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
		Post post=new Post();
		PostEntity postEntity=service.getPostById(id);
		
        BeanUtils.copyProperties(postEntity, post);
        
        Category category=new Category();
        category.setCategoryName(postEntity.getCategory().getCategoryName());
        category.setId(postEntity.getCategory().getId());
        
        post.setCategory(category);
        
		return new ResponseEntity<Post>(post, HttpStatus.OK);
		
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePosts(@PathVariable Integer id) {
		service.deletePost(id);
		return new ResponseEntity<String>("Deleted Success with id:"+id, HttpStatus.OK);		

	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Post>> searchPostsByTitle(@RequestParam String title){
		List<Post> posts=service.searchPosts("%"+title+"%");
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	
	
	
	
}

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.request.Comment;
import com.app.blog.service.CommentService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {
	
	@Autowired
	CommentService service;
	
	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<String> createComment(@RequestBody Comment comment, @PathVariable Integer postId){
		
		Integer id=service.createComment(comment, postId);
		return new ResponseEntity<String>("Comment Success with id:"+id, HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer id){
		service.deleteComment(id);
		return new ResponseEntity<String>("Comment deleted with id:"+id, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/all/{id}")
	public ResponseEntity<List<Comment>> getAllComments(@PathVariable Integer id){
		return new ResponseEntity<List<Comment>>(service.getAllCommentsByPost(id), HttpStatus.OK);
	}

	

}

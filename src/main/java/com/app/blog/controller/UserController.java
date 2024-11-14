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
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.entity.UserEntity;
import com.app.blog.request.User;
import com.app.blog.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
	
	    @Autowired
	    private UserService service;

	    @PostMapping("/create")
	    public ResponseEntity<String> createUser(@RequestBody User user){
	    	Integer id=service.createUser(user);
	    	if(id!=null) {
	    	return new ResponseEntity<String>("Created Success with id:"+id, HttpStatus.CREATED);
	    	}
	    	else {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<User> editUser(@PathVariable Integer id, @RequestBody User user) {
	     boolean  isUpdated=service.updateUser(id, user);
	     if(isUpdated) {
	        
	     return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	    else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	    }
	    
	    
	    @GetMapping("/all")
	    public List<User> getAllUsers(){
	    	List<User> entities=service.getAllUsers();
	    	return entities;
	    }
	    
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
	    	service.deleteUserById(id);
	    	return new ResponseEntity<String>("Deleted Success with id"+id, HttpStatus.OK);
	    }
	    
	    @GetMapping("/single/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable Integer id){
	    	 UserEntity userEntity=service.getUserById(id);
	    	 User user=new User();
	    	 BeanUtils.copyProperties(userEntity, user);
	    	 return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	
}
